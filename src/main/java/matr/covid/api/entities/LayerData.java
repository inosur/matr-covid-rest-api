package matr.covid.api.entities;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.locationtech.jts.geom.Point;

/**
 *
 * @author osvaldo
 */
@Entity
public class LayerData implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "geometry")
    private Point coordinate;
    @Lob 
    @Column(name = "layerData")
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> data;
    private Long layerId;

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

    public Long getLayerId() {
        return layerId;
    }

    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }

}
