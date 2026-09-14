package animal.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {
    public String monNom() throws RemoteException;
    public String monMaitre() throws RemoteException;
    public String monEspece() throws RemoteException;
    public String allInfos() throws RemoteException;
}
