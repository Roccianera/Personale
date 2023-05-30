package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
    
  

    try {
              
        CalutlatorImplE obj= new CalutlatorImplE();
        Registry registry =  LocateRegistry.createRegistry(1099);

        registry.rebind("Calculator", obj);

        System.out.println("Server listening ");


    } catch (RemoteException e) {
        e.printStackTrace();
    }


    }






    
}
