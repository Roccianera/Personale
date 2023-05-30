package client;

import java.rmi.registry.*;

import whiteboard.*;

public class Client1 {


    public static void main(String[] args) {
        try {
            
            


            Registry registry= LocateRegistry.getRegistry();

            WhiteBoard board = (WhiteBoard)registry.lookup("WhiteBoard");


            System.out.println("Got referebce <myWhiteBoard>");
            System.out.println(board.toString());





        Shape shape; int choice;

        for (int i = 0; i < 4; i++) {
            
            choice =(int)(1+Math.random()*10);

            if(choice<=5) 
                shape= new Triangle();
            else
                shape= new Square();
            
            System.out.println("\nAdding the shape("+ choice+")"+ shape.toString());


            board.addShape(shape); 

            Thread.sleep(10000);

        }




        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
}
