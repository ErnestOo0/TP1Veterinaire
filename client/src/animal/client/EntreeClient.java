package animal.client;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EntreeClient {
    //static IObservateur o;//pour avoir le meme pour tous les clients pas bien car meme les clients pas abonés recevront la notif

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        /*
        try {


            IObservateur o = new ObservateurImpl();
            //String nomObserver = "clientObservateur"+idClient;

            Registry registry = LocateRegistry.getRegistry(host, 1099);

            System.out.println("connecte");
            ICabinet cabinetStub = (ICabinet) registry.lookup("cabinet1");
            //cabinet pourait attribuer un id de client
            cabinetStub.abonnement(o);

            IDossierSuivi dossMarcel = new DossierSuiviImpl(EtatSante.BOF);
            dossMarcel.nouvelleObservation(new Observation("ras"));
            dossMarcel.nouvelleObservation(new Observation("toujours impec"));

            IEspece chien = cabinetStub.getEspeceAccepteesByName("chien");

            cabinetStub.nouveauPatient("Marcel", "Ernest", chien, dossMarcel);

            System.out.println("classe du stub : " + cabinetStub.getClass().getName());
            System.out.println("proxy dynamique ? " + Proxy.isProxyClass(cabinetStub.getClass()));

            IDossierSuivi dossBil = new DossierSuiviImpl(EtatSante.PLEINE_FORME);
            dossBil.nouvelleObservation(new Observation("saute partout"));
            cabinetStub.nouveauPatient("Billy", "Ernest", chien, dossBil);

            //System.out.println("response: " + cabinetStub.allInfos() );
            ArrayList<IAnimal> patientsC1 = cabinetStub.getPatients();
            System.out.println("patients cabinet 1 : ");


            for (IAnimal a : patientsC1) {
                System.out.println(a.allInfos());
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }

         */

        CLIVeterinaire cli = new CLIVeterinaire();

        cli.menuAccueil();
    }
}