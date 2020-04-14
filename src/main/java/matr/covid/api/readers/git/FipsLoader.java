package matr.covid.api.readers.git;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author osvaldo
 */
@Component
public class FipsLoader {

    private static final Logger LOG = LoggerFactory.getLogger(FipsLoader.class);

    @Value("classpath:/fips/ZIP-COUNTY-FIPS_2017-06.csv")
    private Resource resource;

    public Map<String, FipsZipcode> doLoad() {

        try {
            CsvToBean<FipsZipcode> build = new CsvToBeanBuilder<FipsZipcode>(new InputStreamReader(resource.getInputStream()))
                    .withType(FipsZipcode.class)
                    .withThrowExceptions(false)
                    .build();

            return build.stream().collect(HashMap<String, FipsZipcode>::new, (mm, pp) -> {
                String key = pp.getZip() + "-" + pp.getFips();
                if (!mm.containsKey(key)) {
                    mm.put(key, pp);
                } else {
                    LOG.info("rejecting repeated entry:{}", pp);
                }

            }, HashMap<String, FipsZipcode>::putAll);

        } catch (IOException ex) {
            LOG.error("Error parsing file.", ex);
        }
        return null;
    }

    public static class FipsZipcode {

        //ZIP,COUNTYNAME,STATE,STCOUNTYFP,CLASSFP
        @CsvBindByName(column = "ZIP")
        private String zip;
        @CsvBindByName(column = "COUNTYNAME")
        private String countyName;
        @CsvBindByName(column = "STATE")
        private String state;
        @CsvBindByName(column = "STCOUNTYFP")
        private String fips;
        @CsvBindByName(column = "CLASSFP")
        private String classFp;

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getFips() {
            return fips;
        }

        public void setFips(String fips) {
            this.fips = fips;
        }

        public String getClassFp() {
            return classFp;
        }

        public void setClassFp(String classFp) {
            this.classFp = classFp;
        }

        @Override
        public String toString() {
            return "FipsZipcode{" + "zip=" + zip + ", countyName=" + countyName + ", state=" + state + ", fips=" + fips + ", classFp=" + classFp + '}';
        }

    }

}
