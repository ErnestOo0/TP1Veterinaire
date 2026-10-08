package animal.client;

import animal.common.IAnimal;
import espece.common.IEspece;
import observateur.common.IObservateur;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class LogiqueClient {
    private IObservateur observateur;
    private Registry registry;

    public LogiqueClient(){
        observateur = null;
        registry = null;
    }

    public LogiqueClient(IObservateur observateur, Registry registry){
        this.observateur = observateur;
        this.registry = registry;
    }


    ArrayList<IAnimal> getAllPatients() {
        return null;
        //to do
    }

    IAnimal getPatientsByName(IAnimal animal) {
        return null;
        //to do
    }

    void ajouterObservation(IObservateur observation, IAnimal animal) {
        //to do
    }

    void ajouterPatient(IAnimal animal) {
        //to do
    }

    IEspece getAllEspeces(){
        return null;
        //to do
    }

    boolean isAbonne(IObservateur o){
        return false;
        //to do
    }

    void abonnement(IObservateur o){
        //to do
    }

    void desabonnement(IObservateur o){
        //to do
    }


}
