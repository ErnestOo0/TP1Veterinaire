package animal.server;

import animal.common.IAnimal;
import dossierSuivi.common.EtatSante;
import dossierSuivi.common.IDossierSuivi;
import espece.common.EspeceImpl;
import espece.common.IEspece;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Server {

    public static final int PORT = 1099;

    public static void main(String[] args) {
        boolean embedded = args.length > 0 && "--embedded".equals(args[0]);
        try {
            IEspece chien = new EspeceImpl("chien",10);
            IDossierSuivi dossMarcel = new DossierSuiviImpl(EtatSante.BOF);

            IAnimal obj = new AnimalImpl("Marcel", "Ernest",chien, dossMarcel);

            Registry registry = embedded
                    ? LocateRegistry.createRegistry(PORT)
                    : LocateRegistry.getRegistry(PORT);

            // rebind plutot que bind : on peut relancer le serveur sans
            // redemarrer le registre (voir TD 1, question 7).
            registry.rebind("animal", obj);
            registry.rebind("dossierM", dossMarcel);

            System.out.println("Server ready (registre "
                    + (embedded ? "interne" : "externe") + ", port " + PORT + ")");

            //TimeUnit.SECONDS.sleep(3);
            System.out.println(chien);
            System.out.println(System.identityHashCode(chien));
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
