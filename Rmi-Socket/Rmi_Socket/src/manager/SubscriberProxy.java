package manager;


import java.io.DataOutputStream;
import java.net.Socket;


import intefaccia.ISubscriber;

public class SubscriberProxy  implements ISubscriber{


    private int porta;
    private int compenentID;

    SubscriberProxy(int porta,int compenentID){

        this.porta=porta;
        this.compenentID=compenentID;
        

        
    
    }


    @Override
    public int getPorta() {
       return porta;
   
    }
    @Override
    public int getComponentID() {
        return compenentID;
 
    }


    public void NotifyAlert(int criticy ){



        try {
            

            Socket socket = new Socket("127.0.0.1",porta);
          //  DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeInt(criticy);
            dataOutputStream.flush();
            socket.close();
        } catch (Exception e) {

            System.out.println("Notify Alert failed");
        }




    }
    



    
}
