package animal.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CLIClient {

    private static final Scanner scanner = new Scanner( System.in );
    ArrayList<String> listText;
    ArrayList<IAffichage> listActions;

    public CLIClient() {
        listText = new ArrayList<>();
        listActions = new ArrayList<>();
    }

    @Deprecated
    int demanderChoix(String textAffich){
        return demanderInt(textAffich);
    }

    void addChoix(String text, IAffichage action){
        listText.add(text);
        listActions.add(action);
    }

    void afficherInterface() throws RemoteException {

        int choix;
        //on affiche toutes les possibilitées avec le nombre a rentrer pour y acceder
        for(int i=0; i<listText.size(); i++){
                System.out.println(listText.get(i) + " [" + (i+1)+ "]");
            }

        choix = demanderInt("Faites votre choix entre 1 et "+listText.size());
        while(choix<1 || choix>listText.size()){
            choix = demanderInt("Choix incorect, entrez une valeur entre 1 et "+listText.size());
        }
        listActions.get(choix-1).run();
    }

    int choixSelection(ArrayList<String> propsitions){
        int choix;
        //on affiche toutes les possibilitées avec le nombre a rentrer pour y acceder
        for(int i=0; i<propsitions.size(); i++){
            System.out.println(propsitions.get(i) + " [" + (i+1)+ "]");
        }

        choix = demanderInt("Faites votre choix entre 1 et "+propsitions.size());
        while(choix<1 || choix>propsitions.size()){
            choix = demanderInt("Choix incorect, entrez une valeur entre 1 et "+listText.size());
        }
        return choix-1;
    }

    static Boolean validation(String textAffich){
        System.out.print(textAffich+" [Y/N]");
        if(scanner.nextLine().equals("Y")){
            return true;
        }
        return false;
    }

    static String demanderString(String textAffich){
        System.out.print(textAffich+" : ");
        return scanner.nextLine();
    }

    static int demanderInt(String textAffich){
        System.out.print(textAffich+" : ");
        try{
            int res = scanner.nextInt();
            scanner.nextLine();//permet d'enlever le \n qui n'est pas pris et de ne pas gener le prochain nextLine
            return res;
        }catch(InputMismatchException e){
            scanner.nextLine();
            System.out.println("veillez renseigner un nombre dans l'interval indiqué");
            return demanderInt(textAffich);
        }

    }

}
