package animal.server;

import animal.common.IAnimal;
import dossierSuivi.common.DossierSuiviImpl;
import dossierSuivi.common.IDossierSuivi;
import espece.common.IEspece;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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

    public String allInfos() throws RemoteException{
        return this.nom + " " + this.nomMaitre + " " + this.espece + " " + this.dossierSuivi.printDossier();
    }
}
