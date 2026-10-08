package animal.client;

import animal.common.IAnimal;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CLIVeterinaire{
    private final LogiqueClient lc;//logique client associée

    public CLIVeterinaire(LogiqueClient lc){
        this.lc = lc;
    }

    void pasImplementePage(){
        System.out.println("Work in progress ...");
        System.out.println("Veillez revenir plus tard");
        System.out.println();
        //System.out.println("Page pas encore implémenté");
    }

    void addObservation(IAnimal animal) throws RemoteException {
        CLIClient c = new CLIClient();
        String observText = c.demanderString("Entrez votre observation ou rien pour annuler");
        if (!observText.isEmpty()){
            lc.ajouterObservation(animal, observText);
            //ajouter les verifs
            System.out.println("Observation ajouté avec succes");
        }
        detailPatient(animal);
    }

    void dltPatient(IAnimal animal) throws RemoteException {
        CLIClient c = new CLIClient();
        if(c.validation("êtes vous sur de vouloir suprimer le patient X")){
            pasImplementePage();
        }
        detailPatient(animal);

    }

    void detailPatient(IAnimal a) throws RemoteException {
        CLIClient c = new CLIClient();
        c.addChoix("supprimer le patient", ()-> dltPatient(a));
        c.addChoix("Ajouter une observation", ()->addObservation(a));
        c.addChoix("Reour", this::menuListPatients);

        c.afficherInterface();
    }

    void menuListPatients() throws RemoteException {

        CLIClient c = new CLIClient();
        System.out.println("entrez le numéro asocié au patient pour accéder à sa fiche");
        c.addChoix("Retour", this::menuPatient);
        ArrayList<IAnimal> listPatients = lc.getAllPatients();

        for(IAnimal a : listPatients){
            c.addChoix(a.monNom(),()->detailPatient(a));
        }

        c.afficherInterface();

    }

    void menuPatient() throws RemoteException {
        CLIClient c = new CLIClient();
        IAffichage menuPatientsDetail = ()-> {
            pasImplementePage();
            menuPatient();
        };

        c.addChoix("Liste des patients", this::menuListPatients);
        c.addChoix("Rechercher un patient", menuPatientsDetail);
        c.addChoix("Ajouter un patient", menuPatientsDetail);
        c.addChoix("Retour", this::menuAccueil);

        c.afficherInterface();
    }

    void quitter(){
        System.out.println("Merci d'avoir utilisé nos services");
        //se desabonner
        System.exit(0);
    }

    void menuAccueil() throws RemoteException {

        CLIClient c = new CLIClient();
        IAffichage menuPasImplemente = ()-> {
            pasImplementePage();
            menuAccueil();
        };

        c.addChoix("Patients", this::menuPatient);
        if(lc.isAbonne()){
            c.addChoix("Abonnement", menuPasImplemente);
        }else{
            c.addChoix("Desabonnement", menuPasImplemente);
        }
        c.addChoix("Quitter", this::quitter);

        c.afficherInterface();
    }
}
