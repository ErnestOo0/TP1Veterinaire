package animal.client;

import animal.common.IAnimal;
import dossierSuivi.common.IDossierSuivi;
import dossierSuivi.common.Observation;
import espece.common.IEspece;

import java.lang.reflect.Proxy;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Client {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            //A1
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            IAnimal animalStub = (IAnimal) registry.lookup("animal");

            System.out.println("classe du stub : " + animalStub.getClass().getName());
            System.out.println("proxy dynamique ? " + Proxy.isProxyClass(animalStub.getClass()));

            System.out.println("response: " + animalStub.allInfos() );

            //A2
            IEspece e = animalStub.monEspece();
            e.setAgeAvgEspece(3);
            System.out.println(System.identityHashCode(e));

            //A3
            IDossierSuivi dsStub = (IDossierSuivi) registry.lookup("dossierM");

            Observation newO= new Observation("ras");
            System.out.println("dossier : "+ dsStub.printDossier());

            dsStub.nouvelleObservation(newO);

            IDossierSuivi dsStub2 = (IDossierSuivi) registry.lookup("dossierM");
            System.out.println("dossier : "+ dsStub2.printDossier());
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}