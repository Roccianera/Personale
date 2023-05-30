package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccie.IDispatcher;

public class DispatcherServer {

    public static void main(String[] args) {


        try {
            
            IDispatcher dispatcher = new DispatcherImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            
            registry.bind("gestore", dispatcher);

            System.out.println("Oggetto remoto online ....");


        } catch (Exception e) {
            e.printStackTrace();
        }


     
    }
    
}
