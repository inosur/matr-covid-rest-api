package matr.covid.api.dto.layer;

import matr.covid.api.dto.ContentInfo;

/**
 *
 * @author osvaldo
 */
public class HealthCareCenterContentInfo implements ContentInfo {

    private String attendance;
    private String description;
    private Integer occupancy;

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

}
