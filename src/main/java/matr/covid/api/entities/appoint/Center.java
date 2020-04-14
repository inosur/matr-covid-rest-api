package matr.covid.api.entities.appoint;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import org.locationtech.jts.geom.Point;

/**
 *
 * @author osvaldo
 */
@Entity
public class Center implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OrderColumn
    private String name;
    private Point location;
    private String postalCode;
    @OneToOne
    private CenterCalendar calendar;
    @OneToOne
    private AppointmentGroup appointmentGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public CenterCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(CenterCalendar calendar) {
        this.calendar = calendar;
    }

    public AppointmentGroup getAppointmentGroup() {
        return appointmentGroup;
    }

    public void setAppointmentGroup(AppointmentGroup appointmentGroup) {
        this.appointmentGroup = appointmentGroup;
    }

}
