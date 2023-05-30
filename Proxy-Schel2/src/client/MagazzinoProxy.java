package client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import interfaccia.IMaggazzino;

public class MagazzinoProxy  implements IMaggazzino{

    private String address ;
    private int port;

    public MagazzinoProxy(String address , int port){
        this.address= address;
        this.port =port;
    }


    @Override
    public void deposita(String articolo, int id) {


        try {
            Socket socket = new Socket(address, port);
            DataOutputStream  dataOutputStream = new DataOutputStream(socket.getOutputStream());
        //    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            dataOutputStream.writeUTF("deposita");
            dataOutputStream.flush();
            dataOutputStream.writeUTF(articolo);
            dataOutputStream.writeInt(id);

            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @Override
    public int preleva(String articolo) {
    
        int id =0 ;

        try {
            Socket socket = new Socket(address, port);
            DataOutputStream  dataOutputStream = new DataOutputStream(socket.getOutputStream());
           DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());


           dataOutputStream.writeUTF("preleva");
           dataOutputStream.flush();
           dataOutputStream.writeUTF(articolo);
           id = dataInputStream.readInt();
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        


        return id;
    }
    
}
