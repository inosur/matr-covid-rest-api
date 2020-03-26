package matr.covid.api.service;

import java.util.ArrayList;
import java.util.List;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.readers.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author osvaldo
 */
@Service
public class LayerService {

    @Autowired
    private List<DataParser> parsers;

    public List<LayerDto> getLayerByCoordinate(Double latitude, Double longitude) {
        return parsers.stream().collect(
                ArrayList::new,
                (p, c) -> p.add(c.getLayer()),
                ArrayList::addAll);
    }

}
