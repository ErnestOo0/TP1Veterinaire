package animal.server;

import animal.common.IAnimal;
import cabinet.common.ICabinet;
import dossierSuivi.common.IDossierSuivi;
import dossierSuivi.common.Observation;
import espece.common.EspeceImpl;
import espece.common.IEspece;
import observateur.common.IObservateur;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CabinetImpl extends UnicastRemoteObject implements ICabinet {
    private final ArrayList<IAnimal> patients;
    private final ArrayList<IEspece>  especesAcceptees;
    private final ArrayList<IObservateur> listObs;

    public CabinetImpl() throws RemoteException {
        super();
        patients = new ArrayList<>();
        especesAcceptees = new ArrayList<>();
        listObs = new ArrayList<>();
    }

    public ArrayList<IAnimal> getPatients() throws RemoteException {
        return patients;
    }

    public boolean isPatient(IAnimal animal) throws RemoteException{
        System.out.println("est contenu ?");
        for(IAnimal p : patients){
            if(p.isEquals(animal)){
                return true;
            }
        }
        return false;
    }

    public IAnimal getPatientByName(String name) throws RemoteException {
        for (IAnimal a : patients) {
            if(a.monNom().equals(name)){
                return a;
            }
        }
        return null;
    }

    private void notifyClients(int seuil) throws RemoteException {
        for(IObservateur o : listObs){
            try{
                o.sueilFranchis(seuil);
            }catch(RemoteException e){
                listObs.remove(o);
            }

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

    public IEspece nouvelleEspeceAcceptee(String nom, int esperanceVie) throws RemoteException {
        IEspece newE = new EspeceImpl(nom, esperanceVie);
        if(!especesAcceptees.contains(newE)){
            especesAcceptees.add(newE);
            return newE;
        }
        System.out.println("espece deja acceptée");
        return newE;
    }

    public IAnimal nouveauPatient(String nom, String nomMaitre, IEspece espece, IDossierSuivi doss) throws RemoteException{
        IAnimal nouvA = new AnimalImpl(nom, nomMaitre, espece, doss);
        addPatient(nouvA);
        return nouvA ;
    }

    private void removePatient(IAnimal a) throws RemoteException {
        System.out.println("remove patient "+a.monNom());
        for(IAnimal p : patients){
            if(p.isEquals(a)){
                patients.remove(p);
                return;
            }
        }
    }

    public void deletePatient(IAnimal patient) throws RemoteException{
        System.out.println("Supression du patient : "+patient.stringInfos());
        System.out.println("nbPatients avant supression = "+patients.size());
        System.out.println("is patient ? "+isPatient(patient));
        removePatient(patient);
        System.out.println("nbPatients apres supression = "+patients.size());

    }

    public boolean isAbonnne(IObservateur obs) throws RemoteException{
        //System.out.println("test abonement : "+listObs.contains(obs));
        return listObs.contains(obs);
    }



    public void abonnement(IObservateur obs) throws RemoteException {
        //System.out.println("Abonement, deja abonné ?: "+ listObs.contains(obs));
        if(!listObs.contains(obs)){
            listObs.add(obs);
        }
    }

    @Override
    public void desabonnement(IObservateur obs) throws RemoteException {
        //System.out.println("Desabonement, deja abonné ?: "+ listObs.contains(obs));
        listObs.remove(obs);
    }

    public void nouvelleObservation(IAnimal animal, String observText) throws RemoteException {
        animal.monDossierSuivi().nouvelleObservation(new Observation(observText));
    }

}
