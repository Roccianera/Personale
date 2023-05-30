package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaccie.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter {

	private static final long serialVersionUID = -54541672194646702L;
    private String filePath;
    Lock lock;



    public PrinterImpl (String filePath) throws RemoteException{
        this.filePath= filePath;
        lock = new ReentrantLock();
    }



    @Override
    public boolean print(String docName) {
  
        if(!lock.tryLock()){

            System.out.println("Printer Occupato");
            return false;
        }

        try {

            
            FileWriter file = new FileWriter(filePath, true);
            BufferedWriter buff = new BufferedWriter(file);
            PrintWriter printWriter = new PrintWriter(buff);

            printWriter.println(docName);
            printWriter.flush();

            printWriter.close();
            buff.close();
            file.close();
            System.out.println("Stampa del docName : " +docName);
            Random r = new Random();

            Thread.sleep(1000*(r.nextInt(6)+5));

            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        
        return true;


    }









    
}
