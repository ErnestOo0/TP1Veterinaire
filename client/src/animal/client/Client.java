package animal.client;

import animal.common.IAnimal;
import cabinet.common.ICabinet;
import dossierSuivi.common.IDossierSuivi;
import dossierSuivi.common.Observation;
import espece.common.IEspece;

import java.lang.reflect.Proxy;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Client {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {

            Registry registry = LocateRegistry.getRegistry(host, 1099);
            ICabinet cabinetStub = (ICabinet) registry.lookup("cabinet1");

            System.out.println("classe du stub : " + cabinetStub.getClass().getName());
            System.out.println("proxy dynamique ? " + Proxy.isProxyClass(cabinetStub.getClass()));

            //System.out.println("response: " + cabinetStub.allInfos() );
            ArrayList<IAnimal> patientsC1 = cabinetStub.getPatients();
            System.out.println("patients cabinet 1 : ");
            for (IAnimal animal : patientsC1) {
                System.out.println(animal.allInfos());
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}