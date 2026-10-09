package animal.common;

import dossierSuivi.common.IDossierSuivi;
import espece.common.IEspece;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {
    public String monNom() throws RemoteException;
    public String monMaitre() throws RemoteException;
    public IEspece monEspece() throws RemoteException;
    public IDossierSuivi monDossierSuivi() throws RemoteException;
    public String stringInfos() throws RemoteException;
    public boolean isEquals(IAnimal animal) throws RemoteException;
}
