package manager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import intefaccia.IManager;

public class ManagerServer {


    public static void main(String[] args) {
        

        
        try {
            
            Registry registry = LocateRegistry.createRegistry(1099);
            IManager manager= new Menager();
            registry.bind("manager",manager);

            System.out.println("Oggetto remoto online.....");

        } catch (Exception e) {
            e.printStackTrace();
        }








    }
    
}
