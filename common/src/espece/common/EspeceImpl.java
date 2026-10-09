package espece.common;

public class EspeceImpl implements IEspece {
    String nom;
    float esperanceMoy;

    public EspeceImpl() {
        super();
    }

    public EspeceImpl(String nom, float esperanceMoy) {
        super();
        this.nom = nom;
        this.esperanceMoy = esperanceMoy;
    }

    public String nomEspece() {
        return nom;
    }
    public float ageAvgEspece(){
        return esperanceMoy;
    }

    public void setAgeAvgEspece(float age){
        esperanceMoy = age;
    }

    public boolean isEquals(IEspece esp){
        if(!nom.equals(esp.nomEspece()))return false;
        if(esperanceMoy != esp.ageAvgEspece())return false;
        return true;
    }

    public String toString() {
        return  nom + " (" + esperanceMoy + ")";
    }
}
