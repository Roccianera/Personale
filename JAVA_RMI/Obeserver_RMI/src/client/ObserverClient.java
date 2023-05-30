package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.MyService;
import service.Observer;

public class ObserverClient {
    

    public static void main(String[] args) {
        
        try {
            
            Registry registry = LocateRegistry.getRegistry();
            MyService stubMyService = (MyService) registry.lookup("myservice");
            System.out.println("Got reference <myservice");

            Observer observer =new ObserverImpl();


            System.out.println("\n Observer with ref :");
            System.out.println(observer.toString());


            stubMyService.attachObserver(observer);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
