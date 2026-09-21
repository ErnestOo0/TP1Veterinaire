package cabinet.common;

import animal.common.IAnimal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ICabinet extends Remote {
    public ArrayList<IAnimal> getPatients() throws RemoteException;
    public IAnimal getPatientByName(String name) throws RemoteException;
    public void addPatient(IAnimal patient) throws RemoteException;
}
