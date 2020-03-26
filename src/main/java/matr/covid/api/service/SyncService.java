package matr.covid.api.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import matr.covid.api.dto.LayerDataDto;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.entities.LayerData;
import matr.covid.api.readers.DataParser;
import matr.covid.api.repository.LayerDataRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private List<DataParser> parsers;



    public void runSync() {
        GeometryFactory geomFactory = new GeometryFactory();
        parsers.stream().forEach(parser -> {

            if (parser.shouldRemoveAllData()) {
                repository.deleteByLayerId(parser.getLayer().getId());
            }

            LayerDto layer = parser.getLayer();
            List<LayerDataDto> readData = parser.readData();

            ArrayList<LayerData> collect = readData.stream().collect(ArrayList<LayerData>::new, (p, layerData) -> {
                LayerData entity = new LayerData();
                entity.setLayerId(layer.getId());
                if (layerData.getCoordinate() != null) {
                    entity.setCoordinate(geomFactory.createPoint(new Coordinate(layerData.getCoordinate().getX(), layerData.getCoordinate().getY())));
                }
                entity.setData(layerData.getData());
                p.add(entity);
            }, ArrayList<LayerData>::addAll);
            repository.saveAll(collect);
        });

    }


}