package matr.covid.api.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Map;
import javax.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author osvaldo
 */
public class HashMapConverter implements AttributeConverter<Map<String, String>, String> {
    
    private static final Logger LOG = LoggerFactory.getLogger(HashMapConverter.class);
    
    @Override
    public String convertToDatabaseColumn(Map<String, String> arg0) {
        try {
            return new ObjectMapper().writeValueAsString(arg0);
        } catch (JsonProcessingException ex) {
            LOG.error("error converting hashmap.", ex);
        }
        return "";
        
    }
    
    @Override
    public Map<String, String> convertToEntityAttribute(String arg0) {
        try {
            return new ObjectMapper().readValue(arg0, Map.class);
        } catch (JsonProcessingException ex) {
            LOG.error("error reading hashmap.", ex);
        }
        return Collections.emptyMap();
    }
    
}
