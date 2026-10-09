package dossierSuivi.common;

import java.io.Serializable;
import java.util.Date;

//rendre serializable
public class Observation implements Serializable {
    private Date dateObservation;
    private String description;

    public Observation() {
        dateObservation = new Date();
        dateObservation.setTime(dateObservation.getTime());
        description = "";
    }

    public Observation(String description) {
        dateObservation = new Date();
        dateObservation.setTime(dateObservation.getTime());
        this.description = description;
    }

    public Observation(Date dateObservation, String description) {
        this.dateObservation = dateObservation;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateObservation() {
        return dateObservation;
    }

    public String printObservation() {
        System.out.println("dossier du client à afficher["+dateObservation.toString() +"] : " + description);
        return "["+dateObservation.toString() +"] : " + description;
    }


    public boolean isEquals(Observation o) {
        if(!(this.dateObservation.equals(o.dateObservation))) return false;
        if(!(this.description.equals(o.description))) return false;
        return true;
    }
}
