package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.WhiteBoard;

public class Server {

    public static void main(String[] args) {

        try {

            System.out.println( "Creating the object..");
            WhiteBoard board =  new WhiteBoardImpl();

            System.out.println(board.toString() + "\n");
            
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("WhiteBoard", registry);
            System.out.println("Obeject registred whith name <myWhiteBoard>\n");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        
    }
    
}
