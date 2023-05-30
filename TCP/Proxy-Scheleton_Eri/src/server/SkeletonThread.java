package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



import service.ICounter;

public class SkeletonThread extends Thread {

    private Socket socket;
    private ICounter counter;


    SkeletonThread(Socket skt, ICounter cnt){
        
        socket= skt ;
        counter =cnt;
    }

    @Override
    public void run() {

        try {
            DataInputStream iStream= new DataInputStream(socket.getInputStream());  
            DataOutputStream oStream = new DataOutputStream(socket.getOutputStream());

            String operation = iStream.readUTF();
            System.out.println("Serviamo l' operazione : " + operation);


            switch (operation) {
                case "sum":
    
                    
                    counter.sum(iStream.readInt());
                    break;
            
                case "get":
    
                    oStream.writeInt(counter.get());
                    oStream.flush();
                    
					/* Flushing a stream ensures that all data that has been written 
						to that stream is output, including clearing any that may have been buffered.
					 */

                    break;
                
                case "sqr":
                    
                    counter.square();
    
                    break;
    
                case "inc" :
    
                    counter.inc();
    
                    break;
                
            }
    

        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }
    
}
