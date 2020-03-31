package matr.covid.api.readers.git;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import matr.covid.api.dto.LayerDataDto;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.readers.DataParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

/**
 *
 * @author osvaldo
 */
@Component
public class CovidByGitDataParser implements DataParser {

    private static final Logger LOG = LoggerFactory.getLogger(CovidByGitDataParser.class);
    private static final Long LAYER_ID = 1L;

    @Value("${covid.git.uri:https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/}")
    private String baseUrl;
    private LayerDto instance;

    @Override
    public LayerDto getLayer() {
        if (instance == null) {
            instance = new LayerDto();
            instance.setId(LAYER_ID);
            instance.setName("Infected Data by state/country");
            instance.setDescription("gets information about infected people around the world.");
        }
        return instance;
    }

    @Override
    public List<LayerDataDto> readData() {
        try {
            List<CovidItemBean> readDailyReport = readDailyReport();
            final ObjectMapper mapper = new ObjectMapper();
            return readDailyReport.stream().
                    collect(ArrayList<LayerDataDto>::new,
                            (p, c) -> {
                                LayerDataDto data = new LayerDataDto();
                                if (c.getLatitude() != null && c.getLongitude() != null) {
                                    data.setCoordinate(new Point(c.getLatitude(), c.getLongitude()));
                                }

                                Map<String, Object> convertValue = mapper.convertValue(c, new TypeReference<Map<String, Object>>() {
                                });
                                data.setData(convertValue);
                                p.add(data);
                            }, ArrayList<LayerDataDto>::addAll);

        } catch (IOException ex) {
            LOG.error("io exception reading data", ex);
            return Collections.emptyList();
        }
    }

    protected List<CovidItemBean> readDailyReport() throws FileNotFoundException, IOException {

        Reader content = null;
        Long currentDay = 0L;
        do {

            String format = LocalDate.now().minusDays(currentDay).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            content = downloadFile(format);
            currentDay++;

        } while (content == null || currentDay > 3);

        if (content == null) {
            return Collections.emptyList();
        }
        return new CsvToBeanBuilder<CovidItemBean>(content).withType(CovidItemBean.class).build().parse();
    }

    protected Reader downloadFile(String dateFile) throws MalformedURLException, IOException {

        String urlString = baseUrl + dateFile + ".csv";
        LOG.info("looking for: {}", urlString);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream openStream = connection.getInputStream();
            return new BufferedReader(new InputStreamReader(openStream));

        } else {
            LOG.info("file {}.csv not found.", dateFile);
        }
        return null;
    }

    @Override
    public boolean shouldRemoveAllData() {
        return true;
    }

    @Override
    public boolean appliesCoordinates() {
        return true;
    }

    @Override
    public int priority() {
        return 0;
    }

}
