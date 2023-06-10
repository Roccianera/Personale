package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaccia.ILogger;

public class LoggerImpl implements ILogger {

    private Lock lock;

    public LoggerImpl(){
        this.lock= new ReentrantLock();
    }
    

    @Override
    public void registraDato(int dato) {
        


        lock.lock();
        try {
            


            FileWriter fw = new FileWriter("Save.txt", true);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter printWriter = new PrintWriter(bw);

    
            printWriter.println("Saved : " +Integer.toString(dato) );

            printWriter.close();
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
            
        
        } finally{

            

            lock.unlock();
        }



        
        
    }






}
