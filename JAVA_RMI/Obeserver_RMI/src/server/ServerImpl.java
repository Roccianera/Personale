package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.MyService;
import service.Observer;

public class ServerImpl extends UnicastRemoteObject implements MyService {


    private Vector<Observer> attachObservers;


    protected final static long serialVersionUID = 10 ;

    public ServerImpl() throws RemoteException{
        attachObservers= new Vector<Observer>();
    }


	@Override
	public void attachObserver(Observer observer) throws RemoteException {

        System.out.println("\n Net Observer attached !\n " + observer.toString());
        attachObservers.add(observer);
		
	}

	@Override
	public void service_method()  throws RemoteException{

        System.out.println("service_method() invoked--1");

        notifyAllObservers();
	}


    private void notifyAllObservers(){

        System.out.println("(new shape ,notify observers!)");

        for (Observer observer : attachObservers) {
            
            try {
                observer.observerNotify();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    
}
