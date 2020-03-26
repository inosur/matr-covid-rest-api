package matr.covid.api.readers.git;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;

/**
 *
 * @author osvaldo
 */
public class CovidItemBean {

    @CsvBindByName(column = "FIPS")
    private String fips;
    @CsvBindByName(column = "Province_State")
    private String state;
    @CsvBindByName(column = "Country_Region")
    private String country;
    @CsvBindByName(column = "Last_Update")
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;
    @CsvBindByName(column = "Confirmed")
    private Long confirmed;
    @CsvBindByName(column = "Deaths")
    private Long Deaths;
    @CsvBindByName(column = "Recovered")
    private Long Recovered;
    @CsvBindByName(column = "Active")
    private Long active;
    @CsvBindByName(column = "Combined_key")
    private String combinedKey;
    @CsvBindByName(column = "Lat")
    private Double Latitude;
    @CsvBindByName(column = "Long_")
    private Double Longitude;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
    }

    public Long getDeaths() {
        return Deaths;
    }

    public void setDeaths(Long Deaths) {
        this.Deaths = Deaths;
    }

    public Long getRecovered() {
        return Recovered;
    }

    public void setRecovered(Long Recovered) {
        this.Recovered = Recovered;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey) {
        this.combinedKey = combinedKey;
    }

}
