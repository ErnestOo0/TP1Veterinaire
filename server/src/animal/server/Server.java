package animal.server;

import animal.common.IAnimal;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static final int PORT = 1099;

    public static void main(String[] args) {
        boolean embedded = args.length > 0 && "--embedded".equals(args[0]);
        try {
            IAnimal obj = new AnimalImpl("Marcel", "Ernest","chien");

            Registry registry = embedded
                    ? LocateRegistry.createRegistry(PORT)
                    : LocateRegistry.getRegistry(PORT);

            // rebind plutot que bind : on peut relancer le serveur sans
            // redemarrer le registre (voir TD 1, question 7).
            registry.rebind("animal", obj);

            System.out.println("Server ready (registre "
                    + (embedded ? "interne" : "externe") + ", port " + PORT + ")");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
