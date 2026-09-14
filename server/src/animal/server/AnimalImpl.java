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
}
