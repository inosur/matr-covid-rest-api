package matr.covid.api.readers.git;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author osvaldo
 */
@SpringBootTest
public class FipsLoaderTest {
    
    @Autowired
    private FipsLoader instance;
    
    public FipsLoaderTest() {
    }
    
    @Test
    public void testDoLoad() {
        System.out.println("doLoad");
        
        Map<String, FipsLoader.FipsZipcode> result = instance.doLoad();
        assertFalse(result.isEmpty());
        
        FipsLoader.FipsZipcode get = result.get("36003-01001");
        assertNotNull(get);
        assertEquals(get.getZip(), "36003");
        assertEquals(get.getFips(), "01001");
    }
    
}
