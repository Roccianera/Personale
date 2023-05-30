package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class HelloServer {

        
    public static void main(String args[]) {
        
        try {

            //HelloServerImplDelega obj_d = new HelloServerImplDelega();
            
            HelloServerImplEreditarieta obj_e = new HelloServerImplEreditarieta(); 
            
            //Hello stub = (Hello) UnicastRemoteObject.exportObject(obj_d, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            // ho cambiato riga di codice da getRegistry a createRegistry cosi non devo neanche instanziarlo da terminale 
            // Usare registry.bind("Hello", stub); per sollevare un'eccezione se l'oggeto è stato
            // già binded
            
            registry.rebind("Hello", obj_e);

            System.err.println("Server ready");
            

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
           // e.printStackTrace();
        }
    }



}


    
