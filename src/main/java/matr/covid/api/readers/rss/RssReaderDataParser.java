package matr.covid.api.readers.rss;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import matr.covid.api.dto.LayerDataDto;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.readers.DataParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author osvaldo
 */
@Component
public class RssReaderDataParser implements DataParser {

    private static final Logger LOG = LoggerFactory.getLogger(RssReaderDataParser.class);
    private static final Long LAYER_ID = 2l;

    @Value("${rss.feed.sources}")
    private List<String> feedSources;
    @Value("${rss.feed.keywords}")
    private List<String> keywords;

    private LayerDto instance;

    @Override
    public LayerDto getLayer() {
        if (instance == null) {
            instance = new LayerDto();

            instance.setName("News Feed");
            instance.setId(LAYER_ID);
            instance.setDescription("News feed from cdc and who.");
            instance.setIcon("");
        }
        return instance;
    }

    @Override
    public List<LayerDataDto> readData() {
        List<NewsFeedData> readRssNews = readRssNews();

        final ObjectMapper mapper = new ObjectMapper();
        return readRssNews.stream().collect(ArrayList<LayerDataDto>::new, (p, c) -> {
            LayerDataDto data = new LayerDataDto();
            data.setCoordinate(null);
            data.setData(mapper.convertValue(c, new TypeReference<Map<String, Object>>() {
            }));

            p.add(data);
        }, ArrayList<LayerDataDto>::addAll);
    }

    protected List<NewsFeedData> readRssNews() {

        List<NewsFeedData> data = new LinkedList<>();

        feedSources.forEach(source -> {
            try {
                URL feedUrl = new URL(source);
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));
                List<SyndEntry> entries = feed.getEntries();

                data.addAll(entries.stream().
                        filter(entry -> rssEntryMatcher(entry)).
                        limit(5).
                        collect(ArrayList<NewsFeedData>::new, (c, entry) -> {
                            NewsFeedData news = new NewsFeedData();
                            news.title = entry.getTitle();
                            news.link = entry.getLink();
                            if (entry.getPublishedDate() != null) {
                                news.date = entry.getPublishedDate();
                            } else {
                                news.date = entry.getUpdatedDate();
                            }
                            news.description = entry.getDescription().getValue();
                            c.add(news);

                        }, ArrayList<NewsFeedData>::addAll));
            } catch (MalformedURLException ex) {
                LOG.error("malformed url", ex);
            } catch (IOException | IllegalArgumentException | FeedException ex) {
                LOG.error("other", ex);
            }

        });
        return data;
    }

    private boolean rssEntryMatcher(SyndEntry entry) {
        String title = entry.getTitle();
        return keywords.stream().anyMatch(keyword -> title.toLowerCase().contains(keyword));
    }

    @Override
    public boolean shouldRemoveAllData() {
        return true;
    }

    @Override
    public boolean appliesCoordinates() {
        return false;
    }

    public static class NewsFeedData {

        public String title;
        public String link;
        public Date date;
        public String description;

        public NewsFeedData() {
        }

        @Override
        public String toString() {
            return "NewsFeedData{" + "title=" + title + ", link=" + link + ", date=" + date + ", description=" + description + '}';
        }

    }
}
