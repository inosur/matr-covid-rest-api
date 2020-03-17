package matr.covid.api.dto.layer;

import matr.covid.api.dto.ContentInfo;

/**
 *
 * @author osvaldo
 */
public class LocationsContentInfo implements ContentInfo {

    private String address;
    private String information;
    private Integer occupancy;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

}
