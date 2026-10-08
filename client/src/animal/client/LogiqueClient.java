package animal.client;

import animal.common.IAnimal;
import cabinet.common.ICabinet;
import dossierSuivi.common.Observation;
import espece.common.IEspece;
import observateur.common.IObservateur;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class LogiqueClient {
    private IObservateur observateur;
    private ICabinet stubCabinet;

    public LogiqueClient(IObservateur observateur, ICabinet stubCabinet){
        this.observateur = observateur;
        this.stubCabinet = stubCabinet;
    }

    ArrayList<IAnimal> getAllPatients() throws RemoteException {
        return stubCabinet.getPatients();//verifications ?
    }

    IAnimal getPatientsByName(String nom) throws RemoteException {
        return stubCabinet.getPatientByName(nom);
    }

    void ajouterObservation(IAnimal animal, String observText) throws RemoteException {
        animal.monDossierSuivi().nouvelleObservation(new Observation(observText));
        //verification
    }

    void ajouterPatient(IAnimal animal) throws RemoteException {
        stubCabinet.addPatient(animal);
        //verification
    }

    ArrayList<IEspece> getAllEspeces() throws RemoteException {
        return stubCabinet.getEspecesAcceptees();
    }

    boolean isAbonne() throws RemoteException {
        return stubCabinet.isAbonnne(observateur);
        //verification
    }

    void abonnement() throws RemoteException {
        stubCabinet.abonnement(observateur);
        //verification
    }

    void desabonnement() throws RemoteException {
        stubCabinet.desabonnement(observateur);
        //verification
    }


}
