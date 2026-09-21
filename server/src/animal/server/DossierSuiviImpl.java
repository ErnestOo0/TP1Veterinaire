package animal.server;

import dossierSuivi.common.EtatSante;
import dossierSuivi.common.IDossierSuivi;
import dossierSuivi.common.Observation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DossierSuiviImpl extends UnicastRemoteObject implements IDossierSuivi {
    private EtatSante etatSante;
    private ArrayList<Observation> historiqueObservations;

    public DossierSuiviImpl() throws RemoteException {
        super();
    }

    public DossierSuiviImpl(EtatSante etatSante) throws RemoteException {
        super();
        this.etatSante = etatSante;
        this.historiqueObservations = new ArrayList<>();
    }

    public EtatSante getEtatSante() throws RemoteException {
        return etatSante;
    }

    public void setEtatSante(EtatSante etatSante) throws RemoteException {
        this.etatSante = etatSante;
    }

    public ArrayList<Observation> getHistorique() throws RemoteException {
        return historiqueObservations;
    }

    public void nouvelleObservation(Observation observation) throws RemoteException {
        historiqueObservations.add(observation);
    }

    public String toString(){
        String s = etatSante.toString();
        for(Observation o : historiqueObservations){
            s += o.toString() + "\n";
        }
        return s;
    }

}
