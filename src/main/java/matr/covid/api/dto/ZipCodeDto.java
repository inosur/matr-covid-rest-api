package matr.covid.api.dto;

/**
 *
 * @author osvaldo
 */
public class ZipCodeDto {

    private Long id;
    private String zip;
    private String countyName;
    private String state;
    private String fips;
    private String classFp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}
