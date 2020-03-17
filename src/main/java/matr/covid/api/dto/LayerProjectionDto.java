package matr.covid.api.dto;

import java.util.List;
import org.springframework.data.geo.Point;

/**
 *
 * @author osvaldo
 */
public class LayerProjectionDto {

    private Integer layerId;
    private Point upperLeft;
    private Point lowerRight;

    private List<DataItemDto> data;

    public Integer getLayerId() {
        return layerId;
    }

    public void setLayerId(Integer layerId) {
        this.layerId = layerId;
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Point getLowerRight() {
        return lowerRight;
    }

    public void setLowerRight(Point lowerRight) {
        this.lowerRight = lowerRight;
    }

    public List<DataItemDto> getData() {
        return data;
    }

    public void setData(List<DataItemDto> data) {
        this.data = data;
    }

}
