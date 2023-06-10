package disk;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import interfaccia.ILogger;

public class ILoggerProxy  implements ILogger{

    private int PORT ;

    public ILoggerProxy(int PORT){
        this.PORT = PORT;
    }



    @Override
    public void registraDato(int dato) {


    try {

        DatagramSocket  socket = new DatagramSocket();
        String message = Integer.toString(dato);
     

        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length , InetAddress.getLocalHost(), PORT);
        socket.send(packet);

        System.out.println("Pacchetto UDP inviato");

        socket.close();



    } catch (Exception e) {
        e.printStackTrace();
    }        
        
    







    }

    
}
