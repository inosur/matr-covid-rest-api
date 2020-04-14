package matr.covid.api.entities.appoint.users;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import matr.covid.api.entities.appoint.Center;

/**
 *
 * @author osvaldo
 */
@Entity
public class CenterUser extends User {

    @ManyToOne
    private Center center;

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

}
