package matr.covid.api.dto.layer;

/**
 *
 * @author osvaldo
 */
public class TestCenterContentInfo {

    private String attendanceTimes;
    private String description;
    private Integer occupancy;

    public String getAttendanceTimes() {
        return attendanceTimes;
    }

    public void setAttendanceTimes(String attendanceTimes) {
        this.attendanceTimes = attendanceTimes;
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
