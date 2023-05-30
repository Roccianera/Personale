package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.MyService;

public class Server {

public static void main(String[] args) {
    try {


        /*
         * Creazione e registazione dell' oggetto remoto.
         */

         System.out.println("Creating the object...");
         MyService myService = new ServerImpl();
         

         System.out.println(myService.toString()+'\n');

         Registry registry =  LocateRegistry.createRegistry(1099);
         
         registry.rebind("myservice",  myService);

         System.out.println(" Object registred with name <myservice> ");

        





    } catch (Exception e) {
        e.printStackTrace();
    }


}

    
}
