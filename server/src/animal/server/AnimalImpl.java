package animal.server;

import animal.common.IAnimal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal {
    private String nom;
    private String nomMaitre;
    private String espece;

    public AnimalImpl() throws RemoteException {
        super();
    }

    public AnimalImpl(String nom, String nomMaitre, String espece) throws RemoteException {
        super();
        this.nom = nom;
        this.nomMaitre = nomMaitre;
        this.espece = espece;
    }

    public String monNom() throws RemoteException {
        return this.nom;
    }

    public String monMaitre() throws RemoteException {
        return this.nomMaitre;
    }

    public String monEspece() throws RemoteException {
        return this.espece;
    }

    public String allInfos() throws RemoteException{
        return this.nom + " " + this.nomMaitre + " " + this.espece;
    }
}
