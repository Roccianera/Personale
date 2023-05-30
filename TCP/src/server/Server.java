package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static final int PORT = 8050;   
    public static void main(String[] args) {
        

        try{
            
            ServerSocket  server  =  new ServerSocket(PORT);
            System.out.println(" Server listening in port : " + PORT);

            while (true) {
                
                Socket skt = server.accept();
                System.out.println ("[Server]: nuovo client, avvio del thread Worker." );





                WorkerServer w = new WorkerServer(skt);
                w.start();

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    
}
