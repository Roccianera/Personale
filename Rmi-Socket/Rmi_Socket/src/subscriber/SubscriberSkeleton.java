package subscriber;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import intefaccia.IManager;
import intefaccia.ISubscriber;

public class SubscriberSkeleton  implements ISubscriber{


    private ISubscriber subscriber;



    public SubscriberSkeleton(ISubscriber subscriber){

        this.subscriber=subscriber;
    }








    public void runSkeleton(){


        try {

            Registry registry = LocateRegistry.getRegistry();

            IManager manager =(IManager) registry.lookup("manager");


            manager.subscribe(getComponentID(), getPorta());
            System.out.println("SottoScritto");


            ServerSocket serverSocket=new ServerSocket(getPorta());

            while (true) {

                Socket socket = serverSocket.accept();

                DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());


                int critic = dataInputStream.readInt();



                FileWriter file =  new FileWriter("alert.txt",true);

                BufferedWriter bufferedWriter= new BufferedWriter(file);
                PrintWriter pWriter= new PrintWriter(bufferedWriter);

                pWriter.println("ID :"+ getComponentID() +" porta :" +getPorta()+ " critc"+ critic);


                pWriter.close();
                bufferedWriter.close();
                file.close();


            
                socket.close();
                
                
            }


            
        } catch (Exception e) {
            e.printStackTrace();

        }






    }




    @Override
    public int getPorta() {
       return subscriber.getPorta();
   
    }
    @Override
    public int getComponentID() {
        return subscriber.getComponentID();
 
    }






    
}
