package matr.covid.api.entities.appoint.users;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import matr.covid.api.entities.appoint.Appointment;
import matr.covid.api.entities.appoint.Person;

/**
 *
 * @author osvaldo
 */
@Entity
public class AppointUser extends User {

    @OneToOne
    private Appointment appointment;
    @OneToOne
    private Person person;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
