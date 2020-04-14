package matr.covid.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import matr.covid.api.dto.LayerDataDto;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.entities.layer.LayerData;
import matr.covid.api.readers.DataParser;
import matr.covid.api.readers.ParserException;
import matr.covid.api.repository.LayerDataRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author osvaldo
 */
@Service
@Transactional
public class SyncService {

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SyncService.class);
    @Autowired
    private LayerDataRepository repository;
    @Autowired
    private ZipCodeService zipCodeService;
    @Autowired
    private List<DataParser> parsers;

    public void runSync() {
        LOG.info("Starting data synchronization from data parsers.");
        GeometryFactory geomFactory = new GeometryFactory();
        parsers.
                stream().
                sorted((c1, c2) -> Integer.compare(c1.priority(), c2.priority())).
                forEach(parser -> {

                    try {
                        LayerDto layer = parser.getLayer();
                        LOG.info("Running parser {}.", parser.getClass().getSimpleName());
                        List<LayerDataDto> readData = parser.readData();
                        if (parser.shouldRemoveAllData()) {
                            LOG.info("Deleting previous data.");
                            repository.deleteByLayerId(parser.getLayer().getId());
                        }
                        LOG.info("Going to synchronize {} entities.", readData.size());
                        ArrayList<LayerData> collect = readData.stream().collect(ArrayList<LayerData>::new, (p, layerData) -> {
                            LayerData entity = new LayerData();
                            entity.setLayerId(layer.getId());
                            if (layerData.getCoordinate() != null) {
                                entity.setCoordinate(geomFactory.createPoint(new Coordinate(layerData.getCoordinate().getX(), layerData.getCoordinate().getY())));
                            }
                            entity.setLayerRef(layerData.getGeoUID());
                            entity.setData(layerData.getData());
                            p.add(entity);
                        }, ArrayList<LayerData>::addAll);
                        repository.saveAll(collect);
                        repository.flush();
                    } catch (ParserException ex) {
                        LOG.info("Parser failed {}.", parser.getClass().getSimpleName());
                    }
                });
        LOG.info("Terminating data synchronization");
        LOG.info("Starting zipcode syncrhonization");
        zipCodeService.synchronizeZipcodes();
        LOG.info("Terminating zipcode syncrhonization.");

    }

}
