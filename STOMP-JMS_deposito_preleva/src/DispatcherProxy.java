import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.jms.*;

public class DispatcherProxy  implements IDispatcher{


    private String address ;
    private int port ;
    private QueueSession queueSession ;
    private Queue queueResponse;


    public DispatcherProxy (String address , int port , QueueSession queueSession, Queue queue){
        this.address= address;
        this.port = port;
        this.queueSession = queueSession;
        this.queueResponse= queue;
    }



    @Override
    public void deposita(int valore) {
        


        try {
            

            Socket socket = new Socket(address, port);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            BufferedReader  dataInBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            data



            
        } catch (Exception e) {
            // TODO: handle exception
        }







        
    }




    @Override
    public int preleva() {
        // TODO Auto-generated method stub
        return 0;
    }











    
}
