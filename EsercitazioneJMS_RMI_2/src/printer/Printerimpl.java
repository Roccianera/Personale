package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaccie.IPrinter;

public class Printerimpl extends UnicastRemoteObject implements IPrinter {

    Lock lock;
    Condition queue;
    boolean full;

    public Printerimpl()throws RemoteException{
        super();
        lock = new ReentrantLock();
        queue = lock.newCondition();
        full =false;

    }

    @Override
    public void printDoc(String docString) throws RemoteException {
        

        try {
            Thread.sleep(5000);
            System.out.println("[PRINTER ] nomeDocumento : " + docString );
    
            lock.lock();
    
            while(full){
                queue.await();
    
            }

            full= true;

            FileWriter fw = new FileWriter("Doc.txt", true);
			BufferedWriter bw = new BufferedWriter(fw); 
			PrintWriter pw = new PrintWriter(bw); 
			
			pw.println(docString);
			pw.flush();
			
			pw.close();
			bw.close();
			fw.close();
    
            full = false;
            queue.signal();
            lock.unlock();
    
    
            
        } catch (Exception e) {
           e.printStackTrace();
        }






    }


    




    
}
