package generator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class Generator {

    public static final int N_THREAD=3;


    public static void main(String[] args) {



        Registry registry;
        try {
            registry = LocateRegistry.getRegistry();
            IDispatcher dispatcher =(IDispatcher) registry.lookup("dispatcher");
            
                    for (int i = 0; i < N_THREAD; i++) {
            
            
                        WorkerThread thread = new WorkerThread(dispatcher);
            
                        thread.start();
            
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }









        
    }
    
}
