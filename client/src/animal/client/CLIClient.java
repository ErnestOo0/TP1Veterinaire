package animal.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIClient {

    private static final Scanner scanner = new Scanner( System.in );
    ArrayList<String> listText;
    ArrayList<IAffichage> listActions;

    public CLIClient() {
        listText = new ArrayList<>();
        listActions = new ArrayList<>();
    }

    int demanderChoix(String textAffich){
        System.out.print(textAffich+" : ");
        return scanner.nextInt();
    }

    void addChoix(String text, IAffichage action){
        listText.add(text);
        listActions.add(action);
    }

    void afficherInterface() throws RemoteException {

        int choix;
        //on affiche toutes les possibilitées avec le nombre a rentrer pour y acceder
        for(int i=0; i<listText.size(); i++){
                System.out.println(listText.get(i) + " : " + (i+1));
            }

            choix = demanderChoix("Faites votre choix entre 1 et "+listText.size());
            while(choix<1 || choix>listText.size()){
                choix = demanderChoix("Choix incorect, entrez une valeur entre 1 et "+listText.size());
            }
        listActions.get(choix-1).run();
    }

    Boolean validation(String textAffich){
        System.out.print(textAffich+" [Y/N]");
        if(scanner.nextLine().equals("Y")){
            return true;
        }
        return false;
    }

    String demanderString(String textAffich){
        System.out.print(textAffich+" : ");
        return scanner.nextLine();
    }

}
