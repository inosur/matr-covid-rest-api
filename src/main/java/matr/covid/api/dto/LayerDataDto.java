package matr.covid.api.dto;

import java.util.Map;
import org.springframework.data.geo.Point;

/**
 *
 * @author osvaldo
 */
public class LayerDataDto {

    private Long id;
    private Point coordinate;
    private Map<String, Object> data;
    private String geoUID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getGeoUID() {
        return geoUID;
    }

    public void setGeoUID(String geoUID) {
        this.geoUID = geoUID;
    }

}
