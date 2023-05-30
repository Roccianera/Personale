package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

    public static final int  PORT_NUMBER=8050;
    
    public static final int  MAX_SIZE_UDP=65508;


    public static void main(String[] args) {
    
        try{
            String s = new String("Hello Server!");

            DatagramSocket socket = new DatagramSocket();
            DatagramPacket  packet = new DatagramPacket(s.getBytes(), s.getBytes().length,InetAddress.getLocalHost(),PORT_NUMBER);
            // uso InetAddress perche sto usando il socket sulla stessa macchina e quindi hanno lo stesso indirizzo IP;

            System.out.println("[Client]: invio  pacchetto UDP....");
            socket.send(packet);
            System.out.println("[Client]:   pacchetto UDP inviato ....");

            byte[]  data = new byte[MAX_SIZE_UDP];
            DatagramPacket response= new DatagramPacket(data, MAX_SIZE_UDP);


            System.out.println("[Client]: attesa   pacchetto UDP....");
            socket.receive(response);
            System.out.println("[Client]:   pacchetto UDP ricevuto  ....");


            String readRespone= new String(response.getData(), 0, response.getLength());
            System.out.println("[Client]  Letura del messaggio : "+ readRespone );

            socket.close();
            		
		}catch ( SocketException e ){
			e.printStackTrace();
		}catch ( IOException e ){
			e.printStackTrace();
		}
		





        
        

    
    }



    
}
