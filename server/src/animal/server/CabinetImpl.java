package animal.server;

import animal.common.IAnimal;
import cabinet.common.ICabinet;
import dossierSuivi.common.IDossierSuivi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CabinetImpl extends UnicastRemoteObject implements ICabinet {
    private ArrayList<IAnimal> patients;

    public CabinetImpl() throws RemoteException {
        super();
        patients = new ArrayList<>();
    }

    public ArrayList<IAnimal> getPatients() throws RemoteException {
        return patients;
    }

    public IAnimal getPatientByName(String name) throws RemoteException {
        for (IAnimal a : patients) {
            if(a.monNom().equals(name)){
                return a;
            }
        }
        return null;
        //gérer l'erreur
    }

    public void addPatient(IAnimal a) throws RemoteException {
        //if nom existe deja, indiquer au client : possibilité de fusionnet les historiques

        patients.add(a);
    }
}
