package animal.client;

import cabinet.common.ICabinet;
import observateur.common.IObservateur;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EntreeClient {
    //static IObservateur o;//pour avoir le meme pour tous les clients pas bien car meme les clients pas abonés recevront la notif

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];

        try{
            IObservateur o = new ObservateurImpl();
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            ICabinet cabinetStub = (ICabinet) registry.lookup("cabinet1");
            LogiqueClient client = new LogiqueClient(o, cabinetStub);
            CLIVeterinaire cli = new CLIVeterinaire(client);

            cli.menuAccueil();
        }catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}