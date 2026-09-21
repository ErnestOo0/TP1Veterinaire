package espece.common;

import java.io.Serializable;

public interface IEspece extends Serializable {
    String nomEspece();
    float ageAvgEspece();
    void setAgeAvgEspece(float age);
    String toString();
}
