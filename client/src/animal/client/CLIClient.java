package animal.client;

import java.util.ArrayList;
import java.util.Scanner;

public class CLIClient {

    private final Scanner scanner;
    public CLIClient() {
        scanner = new Scanner( System.in );
    }

    int demanderChoix(String textAffich, Scanner s){
        System.out.print(textAffich+" : ");
        return s.nextInt();
    }

    void afficherInterface(ArrayList<String> listText, ArrayList<IAffichage> listActions){

        int choix;
        //on affiche toutes les possibilitées avec le nombre a rentrer pour y acceder
        for(int i=0; i<listText.size(); i++){
                System.out.println(listText.get(i) + " : " + (i+1));
            }

            choix = demanderChoix("Faites votre choix entre 1 et "+listText.size(), scanner);
            while(choix<1 || choix>listText.size()){
                choix = demanderChoix("Choix incorect, entrez une valeur entre 1 et "+listText.size(), scanner);
            }
        listActions.get(choix-1).affiche();
    }

}
