package animal.server;

import animal.common.IAnimal;
import cabinet.common.ICabinet;
import dossierSuivi.common.IDossierSuivi;
import espece.common.IEspece;
import observateur.common.IObservateur;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CabinetImpl extends UnicastRemoteObject implements ICabinet {
    private ArrayList<IAnimal> patients;
    private ArrayList<IEspece>  especesAcceptees;
    private HashMap<String, IObservateur> dicoObs;
    private Registry registry;

    public CabinetImpl() throws RemoteException {
        super();
        patients = new ArrayList<>();
        especesAcceptees = new ArrayList<>();
        dicoObs = new HashMap<>();
        registry = null;
    }

    public CabinetImpl(Registry registry) throws RemoteException {
        super();
        patients = new ArrayList<>();
        especesAcceptees = new ArrayList<>();
        dicoObs = new HashMap<>();
        this.registry = registry;
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

    private void notifyClients(int seuil) throws RemoteException {
        for(Map.Entry<String, IObservateur> e : dicoObs.entrySet()){
            System.out.println("Notification "+ e.getKey() +", seuil = "+seuil);
            e.getValue().sueilFranchis(seuil);
        }
    }

    public void addPatient(IAnimal a) throws RemoteException {
        //if nom existe deja, indiquer au client : possibilité de fusionnet les historiques
        //les animaux sont des objets qui agissent comme un id, deux animaux peuvent avoir le meme nom
        if(!patients.contains(a)){
            patients.add(a);
            System.out.println("nb patients = "+patients.size());
            if(patients.size() == 3 || patients.size() == 100 || patients.size() == 500 || patients.size() == 1000){
                notifyClients(patients.size());
            }
        }
    }

    public ArrayList<IEspece> getEspecesAcceptees() throws RemoteException {
        return especesAcceptees;
    }

    public IEspece getEspeceAccepteesByName(String name) throws RemoteException {
        for (IEspece e : especesAcceptees) {
            if(e.nomEspece().equals(name)){
                return e;
            }
        }
        return null;
    }

    public void nouvelleEspeceAcceptee(IEspece e) throws RemoteException {
        if(!especesAcceptees.contains(e)){
            especesAcceptees.add(e);
            return;
        }
        System.out.println("espece deja acceptée");
    }

    public IAnimal nouveauPatient(String nom, String nomMaitre, IEspece espece, IDossierSuivi doss) throws RemoteException{
        IAnimal nouvA = new AnimalImpl(nom, nomMaitre, espece, doss);
        addPatient(nouvA);
        return nouvA ;
    }

    public void abonnement(String nomObservateur) throws RemoteException {
        try{
            System.out.println(nomObservateur+ " abonné");
            IObservateur o = (IObservateur) registry.lookup(nomObservateur);
            dicoObs.put(nomObservateur,o);
            o.sueilFranchis(0);
        }catch(Exception e) {
            System.err.println("Cabinet exception: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void desabonnement(String nomObservateur) throws RemoteException {
        dicoObs.remove(nomObservateur);
    }
}
