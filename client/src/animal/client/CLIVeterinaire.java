package animal.client;

import animal.common.IAnimal;

import java.util.ArrayList;

public class CLIVeterinaire extends CLIClient{
    private LogiqueClient lc;

    public CLIVeterinaire() {
        super();
        lc = null;
    }

    public CLIVeterinaire(LogiqueClient lc){
        this.lc = lc;
    }

    void pasImplementePage(){
        System.out.println("Work in progress ...");
        System.out.println("Veillez revenir plus tard");
        System.out.println();
        //System.out.println("Page pas encore implémenté");
    }

    void menuListPatients(){

    }

    void menuPatient(){
        ArrayList<String> listChoix = new ArrayList<>();
        ArrayList<IAffichage> listActions = new ArrayList<>();
        IAffichage menuPatientsDetail = ()-> {
            pasImplementePage();
            menuPatient();
        };


        listChoix.add("Liste des patients");
        listChoix.add("Rechercher un patient");
        listChoix.add("Ajouter un patient");
        listChoix.add("Retour");

        listActions.add(menuPatientsDetail);
        listActions.add(menuPatientsDetail);
        listActions.add(menuPatientsDetail);
        listActions.add(this::menuAccueil);

        afficherInterface(listChoix, listActions);
    }

    void quitter(){
        System.out.println("Merci d'avoir utilisé nos services");
        //se desabonner
        System.exit(0);
    }

    void menuAccueil(){

        ArrayList<String> listChoix = new ArrayList<>();
        ArrayList<IAffichage> listActions = new ArrayList<>();

        listChoix.add("Patients");
        listChoix.add("Abonnement/Desabonnement");
        listChoix.add("Quitter");

        IAffichage menuPasImplemente = ()-> {
            pasImplementePage();
            menuAccueil();
        };



        listActions.add(this::menuPatient);
        listActions.add(menuPasImplemente);
        listActions.add(this::quitter);

        afficherInterface(listChoix, listActions);
    }
}
