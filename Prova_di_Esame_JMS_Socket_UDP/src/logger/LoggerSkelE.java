package logger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaccia.ILogger;

public abstract class LoggerSkelE implements ILogger {

    private int porta;
    private Lock lock;
    public LoggerSkelE(int porta){
        this.porta =porta;
        lock = new ReentrantLock();
    }
    
    public void runSkeleton(){

  

        try {
            
            DatagramSocket socket = new DatagramSocket(porta);
            System.out.println("[SKEL] Entering   main loop");
            while (true) {
                
                byte[] buffer = new byte[100];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String message = new String(request.getData(),0,request.getLength());
                int dato = Integer.parseInt(message);


                Worker worker = new Worker(lock,dato,this);
                worker.start();

        }
        } catch (Exception e) {
            e.printStackTrace();
        }








    }
}
