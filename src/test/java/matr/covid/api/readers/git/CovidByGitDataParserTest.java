package matr.covid.api.readers.git;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author osvaldo
 */
@SpringBootTest
public class CovidByGitDataParserTest {

    @Autowired
    private CovidByGitDataParser instance;

    @Test
    @DisplayName("testing if the repo exists")
    public void testRepoParse() throws FileNotFoundException, IOException {

     
        List<CovidItemBean> readDailyReport = instance.readDailyReport();

        assertFalse(readDailyReport.isEmpty());

    }

}
