package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {


    public static final int  PORT_NUMBER=8050;
    
    public static final int  MAX_SIZE_UDP=65508;

    public static void main(String[] args) {
        

        try{
            // devo ricevere dal client un pkt ;
            DatagramSocket socket= new DatagramSocket(PORT_NUMBER);
            
            byte[] data = new byte[MAX_SIZE_UDP];

            DatagramPacket pkt = new DatagramPacket(data,data.length);

            System.out.println("[Server]  Aspetto pkt : ");
            socket.receive(pkt);
            System.out.println("[Server] pkt Ricevuto ");

        
            String s=  new String(pkt.getData(),0,pkt.getData().length);
            System.out.println ("[Server]: contenuto pacchetto: " + s);

            s = " OK messaggio ricevuto ";

            DatagramPacket response = new DatagramPacket(s.getBytes(), s.getBytes().length, pkt.getAddress(), pkt.getPort());

            Thread.sleep(5000);

            
            System.out.println("[Server]  invio response : ");
            socket.send(response);
            System.out.println("[Server]  response inviato  ");

            socket.close();

        }catch ( SocketException e ){
			e.printStackTrace();
		}catch ( IOException e ){
			e.printStackTrace();
		}catch ( InterruptedException e ){
			e.printStackTrace();
		}
		
        






    }






    
}
