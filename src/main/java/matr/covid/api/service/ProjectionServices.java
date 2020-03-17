package matr.covid.api.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import matr.covid.api.dto.DataItemDto;
import matr.covid.api.dto.LayerProjectionDto;
import matr.covid.api.dto.layer.InfectedContentInfo;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

/**
 *
 * @author osvaldo
 */
@Service
public class ProjectionServices {
    
    public List<LayerProjectionDto> getProjection(Double latitude, Double longitude) {
        LayerProjectionDto lp1 = new LayerProjectionDto();
        lp1.setLayerId(1);
        
        DataItemDto dti1 = new DataItemDto();
        dti1.setTitle("Covid-19 Argentina Cases");
        dti1.setDescription("Casos de coronavirus en la argentina");
        dti1.setTimestamp(new Date().getTime());
        dti1.setPosition(new Point(-34.642891, -58.50939));
        
        InfectedContentInfo infected = new InfectedContentInfo();
        infected.setActive(63);
        infected.setCases(68);
        infected.setDeath(2);
        infected.setRecovered(3);
        dti1.setContent(infected);
        
        lp1.setData(Arrays.asList(dti1));
        
        return Arrays.asList(lp1);
    }
    
}
