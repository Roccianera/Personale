package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;


public class Client {


    public static final int PORT = 8050;   


    public static void main(String[] args) {

        try {

            Socket socket= new Socket("127.0.0.1", PORT);
            System.out.println("[Client ] creazione del client a buon fine");

            //Stream di byte ;
            DataInputStream fromServer = new DataInputStream(socket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());

            System.out.println("[Client] invio messaggio ");
            Random rand = new Random();
            //METODO PER generare un numero randomico 
            toServer.writeUTF(" Hello Server -"+ rand.nextInt());


            System.out.println("[Client] Aspetto messaggio ");

            System.out.println("[Client ] messaggio ricevuto < " + fromServer.readUTF()+" > .....");


            fromServer.close();
            toServer.close();
            socket.close();
            


            
        } catch (IOException e) {
            e.printStackTrace();
        }



        
    }
    
}
