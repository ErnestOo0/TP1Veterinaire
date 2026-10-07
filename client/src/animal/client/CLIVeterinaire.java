package animal.client;

import animal.common.IAnimal;

import java.util.ArrayList;

public class CLIVeterinaire extends CLIClient{

    public CLIVeterinaire() {
        super();
    }

    void pasImplementePage(){
        System.out.println("Work in progress ...");
        System.out.println("Veillez revenir plus tard");
        System.out.println();
        //System.out.println("Page pas encore implémenté");
    }

    void quitter(){
        System.out.println("Merci d'avoir utilisé nos services");
        System.exit(0);
    }

    void menuAccueil(){

        ArrayList<String> listChoix = new ArrayList<>();
        ArrayList<IAffichage> listActions = new ArrayList<>();

        listChoix.add("Patient");
        listChoix.add("Abonnement/Desabonnement");
        listChoix.add("Quitter");

        IAffichage menuPatients = ()-> {
            pasImplementePage();
            menuAccueil();
        };
        listActions.add(menuPatients);
        listActions.add(menuPatients);
        listActions.add(this::quitter);

        afficherInterface(listChoix, listActions);
    }
}
