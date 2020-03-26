package matr.covid.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import matr.covid.api.dto.LayerDataDto;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.dto.LayerGroupDto;
import matr.covid.api.entities.LayerData;
import matr.covid.api.readers.DataParser;
import matr.covid.api.repository.LayerDataRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.util.GeometricShapeFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author osvaldo
 */
@Service
public class ProjectionServices {

    @Autowired
    private LayerDataRepository layerDataRepository;
    @Autowired
    private List<DataParser> parsers;

    public List<LayerGroupDto> getProjection(Double latitude, Double longitude,Long radius) {

        final ModelMapper mapper = new ModelMapper();

        GeometricShapeFactory factory = new GeometricShapeFactory();
        factory.setNumPoints(32);
        factory.setCentre(new Coordinate(latitude, longitude));
        factory.setSize(2*radius); //~400km
        Polygon circle = factory.createCircle();
        List<LayerGroupDto> collect = parsers.stream().collect(ArrayList<LayerGroupDto>::new, (p, c) -> {
            LayerDto layer = c.getLayer();

            LayerGroupDto layerGroupDto = new LayerGroupDto();
            layerGroupDto.setLayer(layer);
            List<LayerData> layerData;
            if (c.appliesCoordinates()) {
                layerData = layerDataRepository.getByLayerIdAndPosition(layer.getId(), circle, PageRequest.of(0, 20));
            } else {
                layerData = layerDataRepository.findAllByLayerId(layer.getId());
            }
            layerGroupDto.setData(layerData.stream().collect(ArrayList<LayerDataDto>::new, (list, data) -> {
                LayerDataDto map = mapper.map(data, LayerDataDto.class);
                list.add(map);
            }, ArrayList::addAll));
            p.add(layerGroupDto);
        }, ArrayList::addAll);

        return collect;
    }

}
