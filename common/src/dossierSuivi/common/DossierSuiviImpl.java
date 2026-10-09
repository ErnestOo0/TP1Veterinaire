package dossierSuivi.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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

    public String printDossier() throws RemoteException {
        String s = "("+ etatSante.toString();
        if (historiqueObservations.size() <= 0){
            return s + ")";
        }
        s += "\nHistorique : {";
        for(Observation o : historiqueObservations){
            s += o.printObservation() + "\n";
        }
        s += "})";
        return s;
    }

    public boolean isEquals(IDossierSuivi o) throws RemoteException {
        if(!this.printDossier().equals(o.printDossier()))return false;
        return true;
    }
}
