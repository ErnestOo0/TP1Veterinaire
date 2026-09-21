package dossierSuivi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IDossierSuivi extends Remote {
    public EtatSante getEtatSante() throws RemoteException;
    public void setEtatSante(EtatSante etatSante) throws RemoteException;
    public ArrayList<Observation> getHistorique() throws RemoteException;
    public void nouvelleObservation(Observation o) throws RemoteException;
    public String printDossier() throws RemoteException;
}
