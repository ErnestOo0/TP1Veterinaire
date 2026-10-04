package animal.client;

import observateur.common.IObservateur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObservateurImpl extends UnicastRemoteObject implements IObservateur {
    //rajouter un champ cabinet pour savoir au quel il appartient ?
    public ObservateurImpl() throws RemoteException {
        super();
    }

    @Override
    public void sueilFranchis(int v) throws RemoteException {
        System.out.println("[NOTIFICATION] Seuil franchis. : "+ v +" patients");
    }
}
