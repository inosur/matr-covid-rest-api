package matr.covid.api.dto;

import java.util.List;

/**
 *
 * @author osvaldo
 */
public class LayerGroupDto {

    private LayerDto layer;
    private List<LayerDataDto> data;
    private Long timestamp;

    public LayerDto getLayer() {
        return layer;
    }

    public void setLayer(LayerDto layer) {
        this.layer = layer;
    }

    public List<LayerDataDto> getData() {
        return data;
    }

    public void setData(List<LayerDataDto> data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
