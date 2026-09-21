package dossierSuivi.common;

import java.util.Date;

//rendre serializable
public class Observation {
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

    public String toString() {
        return "["+dateObservation.toString() +"] : " + description;
    }
}
