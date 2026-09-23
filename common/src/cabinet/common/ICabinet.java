package cabinet.common;

import animal.common.IAnimal;
import dossierSuivi.common.IDossierSuivi;
import espece.common.IEspece;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ICabinet extends Remote {
    public ArrayList<IAnimal> getPatients() throws RemoteException;
    public IAnimal getPatientByName(String name) throws RemoteException;
    public void addPatient(IAnimal patient) throws RemoteException;
    public IAnimal nouveauPatient(String nom, String nomMaitre, IEspece espece, IDossierSuivi doss) throws RemoteException;
    public ArrayList<IEspece> getEspecesAcceptees() throws RemoteException;
    public IEspece getEspeceAccepteesByName(String name) throws RemoteException;
    public void nouvelleEspeceAcceptee(IEspece e) throws RemoteException;

}
