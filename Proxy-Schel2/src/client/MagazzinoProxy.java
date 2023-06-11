package client;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

        /* 
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
        */

        try {
            
            DatagramSocket socket = new DatagramSocket();
    
            String mess = new String(articolo+"-"+Integer.toString(id));
    
            DatagramPacket packet = new DatagramPacket(mess.getBytes(), mess.getBytes().length,  InetAddress.getLocalHost(), port);

            socket.send(packet);
            

            



            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }






        
    }

    @Override
    public int preleva(String articolo) {
    
        int id =0 ;


        /* 
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
        


        */



        try {
            
            DatagramSocket socket = new DatagramSocket();
    
            String mess = new String(articolo);
    
            DatagramPacket packet = new DatagramPacket(mess.getBytes(), mess.getBytes().length,  InetAddress.getLocalHost(), port);


            socket.send(packet);

            byte[] buff = new byte[1024];

            packet = new DatagramPacket(buff, buff.length);

            socket.receive(packet);
        
            String response = new String(packet.getData(), 0, packet.getLength());

            id = Integer.parseInt(response);
        
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }





        return id;
    }
    
}
