package animal.client;

import animal.common.IAnimal;
import dossierSuivi.common.EtatSante;
import espece.common.IEspece;

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
        String observText = CLIClient.demanderString("Entrez votre observation ou rien pour annuler");
        if (!observText.isEmpty()){
            lc.ajouterObservation(animal, observText);
            //ajouter les verifs
            System.out.println("Observation ajouté avec succes");
        }
        detailPatient(animal);
    }

    void dltPatient(IAnimal patient) throws RemoteException {
        if(CLIClient.validation("êtes vous sur de vouloir suprimer le patient X")){
            lc.supprimerPatient(patient);
            System.out.println("Patient supprimé");
            menuPatient();
        }else{
            detailPatient(patient);
        }
    }

    void detailPatient(IAnimal a) throws RemoteException {
        CLIClient c = new CLIClient();
        //print le dossier
        c.addChoix("supprimer le patient", ()-> dltPatient(a));
        c.addChoix("Ajouter une observation", ()->addObservation(a));
        c.addChoix("Retour", this::menuPatient);

        c.afficherInterface();
    }

    @Deprecated
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

    IEspece ajoutEspece() throws RemoteException {
        String nom = CLIClient.demanderString("Nom de l'espece");
        int esperenceVIe = CLIClient.demanderint("esperence de vie");
        return lc.ajouterEspece(nom, esperenceVIe);
    }

    @Deprecated
    IEspece selectEspece() throws RemoteException {
        CLIClient c = new CLIClient();
        ArrayList<IEspece> listEspeces = lc.getAllEspeces();
        if(listEspeces.isEmpty()){
            System.out.println("Aucune espece enregistrée");
            return null;
        }
        ArrayList<String> nomEspeces = new ArrayList<>();
        for(IEspece e : listEspeces){
            nomEspeces.add(e.nomEspece());
        }
        return listEspeces.get(c.choixSelection(nomEspeces));

        //ajouter espece
    }


    IEspece menuEspeces() throws RemoteException {
        CLIClient c = new CLIClient();
        ArrayList<String> propals = new ArrayList<>();
        propals.add("Ajouter une espece");

        ArrayList<IEspece> listEspeces = lc.getAllEspeces();
        if(listEspeces.isEmpty()){
            System.out.println("Aucune espece enregistrée");
        }
        for(IEspece e : listEspeces){
            propals.add(e.nomEspece());
        }

        int choix = c.choixSelection(propals);
        if(choix == 0){
            return ajoutEspece();
        }

        return listEspeces.get(choix - 1);

    }

    String selectNom() {
        return CLIClient.demanderString("Entrez le nom du patient");
    }

    String selectNomMaitre(){
        return CLIClient.demanderString("Entrez le nom du maitre");
    }

    EtatSante SelectEtatSante(){
        CLIClient c = new CLIClient();
        ArrayList<String> listEtats = new ArrayList<>();
        for(EtatSante e : EtatSante.values()){
            listEtats.add(e.toString());
        }
        return EtatSante.valueOf(listEtats.get(c.choixSelection(listEtats)));
    }

    void recapNewPatient(IEspece e, String n, String m, EtatSante es){
        System.out.println("Espece : "+e.nomEspece());
        System.out.println("Nom : "+n);
        System.out.println("Maitre : "+m);
        System.out.println("Etat de sante : "+es);
    }

    void addPatient()throws RemoteException {

        IEspece e = menuEspeces();
        String n = selectNom();
        String m = selectNomMaitre();
        EtatSante es = SelectEtatSante();
        recapNewPatient(e,n,m,es);
        if(CLIClient.validation("AJouter le patient ?")){
            lc.ajouterPatient(n,m,e,es);
            System.out.println(n+" ajouté avec succes");
        }else{
            System.out.println("Annulation de la création de patient, retour au menu");
        }
        menuPatient();
    }

    void chercherPatient() throws RemoteException {
        String patientName = CLIClient.demanderString("entrez le nom d'un patient");
        IAnimal patient = lc.getPatientByName(patientName);
        if(patient != null){
            detailPatient(patient);
        }else{
            System.out.println("Patient "+patientName+" inconnu");
            menuPatient();
        }
    }

    void menuPatient() throws RemoteException {
        CLIClient c = new CLIClient();
        IAffichage menuPatientsDetail = ()-> {
            pasImplementePage();
            menuPatient();
        };

        ArrayList<IAnimal> listPatients = lc.getAllPatients();

        if(listPatients.isEmpty()){
            System.out.println("Aucun patient");

        }

        c.addChoix("Rechercher un patient", this::chercherPatient);
        c.addChoix("Ajouter un patient", this::addPatient);
        for(IAnimal a : listPatients){
            c.addChoix(a.monNom(),()->detailPatient(a));
        }
        c.addChoix("Retour", this::menuAccueil);

        c.afficherInterface();
    }

    void abonnement()throws RemoteException {
        lc.abonnement();
        System.out.println("Vous etes désormais abonné");
        menuAccueil();
    }

    void desabonnement()throws RemoteException {
        lc.desabonnement();
        System.out.println("Désabonnement effectué");
        menuAccueil();
    }

    void quitter() throws RemoteException {
        System.out.println("Merci d'avoir utilisé nos services");
        //se desabonner
        if(lc.isAbonne()){
            lc.desabonnement();
        }
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
            c.addChoix("Abonnement", this::abonnement);
        }else{
            c.addChoix("Desabonnement", this::desabonnement);
        }
        c.addChoix("Quitter", this::quitter);

        c.afficherInterface();
    }
}
