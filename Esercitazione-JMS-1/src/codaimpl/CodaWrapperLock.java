package codaimpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import coda.*;

public class CodaWrapperLock extends CodaWrapper{

    private Lock lock;

    private Condition empty;
    private Condition full;


    public CodaWrapperLock( Coda c){
        super(c);

        lock = new ReentrantLock();

        empty = lock.newCondition();
        full = lock.newCondition();
    }




    @Override
    public void inserisci(int i) {

        lock.lock();

        try {


            while (coda.full()) {
                System.out.println("Coda piena ");


                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    
                    
            }

            coda.inserisci(i);
            full.signal();

            
        } finally {

            lock.unlock();
        }




    }

    @Override
    public int preleva() {

        int prelievo =0;

        lock.lock();


        try {

            while(coda.empty()){

                System.out.println("Coda vuota ");

                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            prelievo = coda.preleva();
            empty.signal();
            
        }finally{
            lock.unlock();
        }

        return prelievo;

    }


    
}
