package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IDispatcher;
import interfaces.IObserver;
import interfaces.IReading;

public class Dispatcher extends UnicastRemoteObject implements IDispatcher {




    private Hashtable <String,ArrayList<IObserver>> topics;
    private Lock lock;
    private IReading Reading;


    public Dispatcher() throws RemoteException{

        this.topics= new Hashtable<>();

        topics.put("temperatura", new ArrayList<IObserver>());
        topics.put("pressione", new ArrayList<IObserver>());

        lock= new ReentrantLock();

        
    }


    @Override
    public void setReading(IReading reading) throws RemoteException {
        

        lock.lock();
        Reading=reading;

        NotifyAll(reading.getType());

        try {

            Random r = new Random();
            Thread.sleep(1000*(r.nextInt(5)+1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();

    }

    @Override
    public IReading getReading() throws RemoteException {
        return Reading;
    }

    


    @Override
    public void attach(IObserver observer, String topic) throws RemoteException {
        

        (topics.get(topic)).add(observer);

    }


    private void NotifyAll(String topic){

        for (IObserver observer : topics.get(topic) ) {
            
            try {
                observer.notifyReading();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


    }








    
}
