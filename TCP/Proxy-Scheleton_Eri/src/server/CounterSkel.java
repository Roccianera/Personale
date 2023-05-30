package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.ICounter;

public   abstract class CounterSkel  implements ICounter{

    public void runSkeleton(){
        ServerSocket serverSocket =null;
        Socket socket= null;

        try{

            serverSocket = new ServerSocket(2500);
            System.out.println("Server in ascolto sulla porta 2500");

            while( true){
                socket = serverSocket.accept();
                SkeletonThread st = new SkeletonThread(socket,this);
                // passando This noi passiamo in modo CounterImpl ;
                st.start();
            }
        } catch( IOException e){
            e.printStackTrace();
        }
        
    }
    
}
