package matr.covid.api.service;

import java.util.Arrays;
import java.util.List;
import matr.covid.api.dto.LayerDto;
import org.springframework.stereotype.Service;

/**
 *
 * @author osvaldo
 */
@Service
public class LayerService {

    public List<LayerDto> getLayerByCoordinate(Double latitude, Double longitude) {
        LayerDto l1 = new LayerDto();
        l1.setId(1L);
        l1.setName("cases");
        l1.setDescription("cases layer description");
        l1.setIcon("https://www.clipartmax.com/png/middle/55-552468_biohazard-svg-png-icon-free-download-biohazard-symbol.png");
        LayerDto l2 = new LayerDto();
        l2.setId(2l);
        l2.setName("test_centers");
        l2.setName("test centers layer");
        l2.setIcon("https://banner2.cleanpng.com/20180710/akz/kisspng-computer-icons-icon-design-test-uppcs-exam-pattern-5b4578cea4bb17.9280914015312795666747.jpg");
        return Arrays.asList(l1, l2);
    }

}
