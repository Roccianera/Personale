package logger;

import java.util.concurrent.locks.Lock;

import interfaccia.ILogger;

public class Worker  extends Thread{


    private Lock lock;
    private int  dato ;
    ILogger logger;
    public Worker( Lock lock , int dato , ILogger logger){
        this.lock =lock;
        this.dato =dato;
        this.logger =logger;
    }


    @Override
    public void run() {


        
        
        try {
            lock.lock();
            logger.registraDato(dato);

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }







    }
    
}
