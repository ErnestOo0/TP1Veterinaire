package animal.client;

import animal.common.IAnimal;

import java.rmi.RemoteException;

//interface fonctionnnelle pour la lambda
public interface IAffichage {
    void run() throws RemoteException;
}
