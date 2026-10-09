package animal.server;

import cabinet.common.ICabinet;
import espece.common.EspeceImpl;
import espece.common.IEspece;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static final int PORT = 1099;

    public static void main(String[] args) {
        boolean embedded = args.length > 0 && "--embedded".equals(args[0]);
        try {

            Registry registry = embedded
                    ? LocateRegistry.createRegistry(PORT)
                    : LocateRegistry.getRegistry(PORT);


            ICabinet c1 = new CabinetImpl();

            registry.rebind("cabinet1", c1);

            System.out.println("Server ready (registre "
                    + (embedded ? "interne" : "externe") + ", port " + PORT + ")");


        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
