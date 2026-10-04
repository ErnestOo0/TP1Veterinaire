package observateur.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservateur extends Remote {
    public void sueilFranchis(int v) throws RemoteException;
}
