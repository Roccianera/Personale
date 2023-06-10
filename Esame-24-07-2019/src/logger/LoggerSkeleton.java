package logger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


import interfaccia.ILogger;

public class LoggerSkeleton implements ILogger {
    
    private ILogger  logger;
    private int PORT;

    public LoggerSkeleton(ILogger logger, int PORT){
        this.PORT =PORT;
        this.logger = logger;
    }
    

    @Override
    public void registraDato(int dato) {
        logger.registraDato(dato);
    }

    public void run_skeleton(){


        try {
            DatagramSocket  socketServer = new DatagramSocket(PORT);
            System.out.println("Entering main loop");

            while (true) {

                byte [] buff = new byte[100];
                DatagramPacket packet = new DatagramPacket(buff,buff.length);
                socketServer.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                WorkerThread thread = new WorkerThread(this, message);
                thread.start();
                
            }


    
        } catch (Exception e) {
            e.printStackTrace();
        } 











    }



}
