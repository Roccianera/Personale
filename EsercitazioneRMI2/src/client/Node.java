package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccie.IDispatcher;

public class Node {




    public static void main(String[] args) {
    
        try {    
            Registry registry;
            registry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = (IDispatcher) registry.lookup("gestore");
    
            for (int i = 0; i < 5; i++) {
                
                NodeThread thread = new NodeThread(dispatcher);
                thread.start();
            }


        } catch (Exception e) {
       
            e.printStackTrace();
        }
    }

    
}
