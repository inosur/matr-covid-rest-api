package matr.covid.api.readers;

import java.util.List;
import matr.covid.api.dto.LayerDataDto;
import matr.covid.api.dto.LayerDto;

/**
 *
 * @author osvaldo
 */
public interface DataParser {

    /**
     * gets the available layer.
     *
     * @return
     */
    LayerDto getLayer();

    /**
     * reads and compute the data according to the layer.
     *
     * @return
     */
    List<LayerDataDto> readData();

    boolean shouldRemoveAllData();

    public boolean appliesCoordinates();
    
    

}
