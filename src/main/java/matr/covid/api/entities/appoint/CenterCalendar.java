package matr.covid.api.entities.appoint;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author osvaldo
 */
@Entity
public class CenterCalendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "calendar_from")
    private Date from;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "calendar_to")
    private Date to;
    @Column(name = "apt_timeout")
    private Integer appointmentTimeout;
    @OneToMany
    private List<Capacity> capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Integer getAppointmentTimeout() {
        return appointmentTimeout;
    }

    public void setAppointmentTimeout(Integer appointmentTimeout) {
        this.appointmentTimeout = appointmentTimeout;
    }

    public List<Capacity> getCapacity() {
        return capacity;
    }

    public void setCapacity(List<Capacity> capacity) {
        this.capacity = capacity;
    }

}
