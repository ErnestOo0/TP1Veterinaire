package animal.server;

import animal.common.IAnimal;
import dossierSuivi.common.DossierSuiviImpl;
import dossierSuivi.common.IDossierSuivi;
import espece.common.IEspece;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal {
    private String nom;
    private String nomMaitre;
    private IEspece espece;
    private IDossierSuivi dossierSuivi;

    public AnimalImpl() throws RemoteException {
        super();
    }

    public AnimalImpl(String nom, String nomMaitre, IEspece espece) throws RemoteException {
        super();
        this.nom = nom;
        this.nomMaitre = nomMaitre;
        this.espece = espece;
        dossierSuivi = new DossierSuiviImpl();
    }

    public AnimalImpl(String nom, String nomMaitre, IEspece espece, IDossierSuivi dossier) throws RemoteException {
        super();
        this.nom = nom;
        this.nomMaitre = nomMaitre;
        this.espece = espece;
        this.dossierSuivi = dossier;
    }


    public String monNom() throws RemoteException {
        return this.nom;
    }

    public String monMaitre() throws RemoteException {
        return this.nomMaitre;
    }

    public IEspece monEspece() throws RemoteException {
        return this.espece;
    }

    public IDossierSuivi monDossierSuivi() throws RemoteException{
        return this.dossierSuivi;
    }

    public String stringInfos() throws RemoteException{
        String res = "Nom : " + this.nom + "\n";
        res += "Maitre : " + this.nomMaitre + "\n";
        res += "Espece : " + this.espece + "\n";
        res += "DossierSuivi : " + this.dossierSuivi.printDossier() + "\n";
        System.out.println("DossierSuivi : " + this.dossierSuivi.printDossier() + "\n");
        return res;
    }

    public boolean isEquals(IAnimal animal) throws RemoteException {
        if(!(this.nom.equals(animal.monNom()))) return false;
        if(!(this.nomMaitre.equals(animal.monMaitre()))) return false;
        if(!(this.espece.isEquals(animal.monEspece())))return false;
        if(!(this.dossierSuivi.isEquals(animal.monDossierSuivi())))return false;
        return true;
    }
}
