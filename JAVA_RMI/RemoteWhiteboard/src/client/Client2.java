package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.Observer;
import whiteboard.WhiteBoard;

public class Client2 {


    public static void main(String[] args) {

        try {
                    
        Registry registry= LocateRegistry.getRegistry();
        WhiteBoard board = (WhiteBoard)registry.lookup("WhiteBoard");
        System.out.println("Got referebce <myWhiteBoard>");
        System.out.println(board.toString());

        Observer observer = new ObserverImpl(board);

        System.out.println("\n Observer with ref : ");
        System.out.println(observer.toString());
        board.attachObserver(observer);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
