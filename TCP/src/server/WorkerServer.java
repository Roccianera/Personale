package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WorkerServer extends Thread {

    private Socket socket ;

    WorkerServer( Socket s){
        socket=s;   
    }

    @Override
    public void run() {
        
        try{
            DataInputStream fromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream toClient  = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("[Server-Worker ] lettura del massaggio : ");

            System.out.println("[Server-Worker] messaggio ricevuto < " + fromClient.readUTF()+ "> . Invio Risposta ");

            toClient.writeUTF("Messaggio letto ");

            fromClient.close();
            toClient.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }          
    }



}
