package matr.covid.api.dto.layer;

import matr.covid.api.dto.ContentInfo;

/**
 *
 * @author osvaldo
 */
public class InfectedContentInfo implements ContentInfo {

    private Integer cases;
    private Integer death;
    private Integer recovered;
    private Integer active;

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
