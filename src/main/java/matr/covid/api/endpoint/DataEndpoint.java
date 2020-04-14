package matr.covid.api.endpoint;

import io.swagger.annotations.ApiParam;
import matr.covid.api.dto.LayerGroupDto;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.service.ProjectionServices;
import matr.covid.api.service.LayerService;
import java.util.List;
import matr.covid.api.dto.ZipCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author osvaldo
 */
@RestController
@RequestMapping("data")
public class DataEndpoint {

    @Autowired
    private LayerService layerServices;
    @Autowired
    private ProjectionServices projectionServices;

    @PostMapping("by-coordinate")
    public List<LayerGroupDto> byCoordinate(@RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam(defaultValue = "10", name = "radius") Long radius) {

        return projectionServices.getProjection(latitude, longitude, radius);
    }

    @PostMapping("available-layers")
    public List<LayerDto> getAvailableLayers(@RequestParam("latitude") final Double latitude,
            @RequestParam("longitude") final Double longitude) {

        return layerServices.getLayerByCoordinate(latitude, longitude);
    }

    @PostMapping("search-zipcode")
    public List<ZipCodeDto> searchZipCode(@RequestParam("criteria") final String criteria) {
        return projectionServices.searchZipcode(criteria);
    }

    @PostMapping("by-zipcode-id")
    public List<LayerGroupDto> getByZipcode(@RequestParam("zipcode") final Long zipcode,
            @RequestParam(defaultValue = "10", name = "radius") Long radius) {

        return projectionServices.getLayerByZipcode(zipcode, radius);
    }

}
