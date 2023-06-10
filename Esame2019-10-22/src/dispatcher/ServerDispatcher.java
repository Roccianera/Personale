package dispatcher;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerDispatcher {


    public static void main(String[] args) {


        
        try {
            
            Registry registry =  LocateRegistry.createRegistry(1099);

            registry.bind("dispatcher", new Dispatcher());


            System.out.println("[DispatcherServer] oggetto remoto online....");


        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        


        


    }
    
}
