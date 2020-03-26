package matr.covid.api.readers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author osvaldo
 */
@Service
public class ParserManager {
    
    @Autowired
    private List<DataParser> parsers;
    
    
    
}
