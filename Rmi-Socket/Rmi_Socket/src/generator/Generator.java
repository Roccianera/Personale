package generator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import intefaccia.IManager;

public class Generator {




    public static void main(String[] args) {
        
        try {
            
            Registry registry = LocateRegistry.getRegistry();

            IManager manager =(IManager) registry.lookup("manager");


            for (int i = 0; i < 3; i++) {
                WorkerThread thread =  new WorkerThread(manager);
                thread.start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
            

    }

    
}
