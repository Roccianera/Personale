package disk;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import interfaccia.ILogger;

public class LoggerProxy   implements ILogger{


    private int porta;
    private DatagramSocket socket;
     public LoggerProxy(int porta) {
        this.porta=porta;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
      
            e.printStackTrace();
        }
     }


    @Override
    public void registraDato(int dato) {

        String message = Integer.toString(dato);

        try {
            DatagramPacket request = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getLocalHost(),porta);
            socket.send(request);

        } catch (Exception e) {
            e.printStackTrace();
        }





        
        
    }
    
}
