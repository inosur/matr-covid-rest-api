package matr.covid.api.readers.rss;

import java.util.List;
import matr.covid.api.readers.ParserException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author osvaldo
 */
@SpringBootTest
public class RssReaderDataParserTest {

    @Autowired
    private RssReaderDataParser instance;

    @Test
    public void testGetLayer() {
        try {
            System.out.println("getLayer");

            List<RssReaderDataParser.NewsFeedData> readRssNews = instance.readRssNews();

            assertFalse(readRssNews.isEmpty());
            assertEquals(readRssNews.size(), 10);
        } catch (ParserException ex) {
            fail("some exceptions raised here");
        }
    }

}
