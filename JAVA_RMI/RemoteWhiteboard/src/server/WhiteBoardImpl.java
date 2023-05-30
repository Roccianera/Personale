package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

import whiteboard.*;

public class WhiteBoardImpl extends UnicastRemoteObject implements WhiteBoard {

    protected WhiteBoardImpl() throws RemoteException {
        super();
        count =0;
        boardContent= new Vector<Shape>();
        attachedObservers= new Vector<Observer>();

        
    }

    private int count;
    private Vector<Shape> boardContent;
    private Vector<Observer> attachedObservers;
    
    protected final static long serialVersionUID =10;
    

    @Override
    public void addShape(Shape s) throws RemoteException {

        count++;
        System.out.println("\n Adding the shape #" + count + " "+ s.toString());

        s.draw();

        boardContent.add(s);

        

        notifyAllObserves();



    }

    @Override
    public void attachObserver(Observer observer) throws RemoteException {

        System.out.println("Added observer");
        attachedObservers.add(observer);
    }

    @Override
    public Vector<Shape> getShapes() throws RemoteException {

        return boardContent;

    }






    private void notifyAllObserves(){


        System.out.println("new shape, notify observers!");

        for (Observer observer : attachedObservers) {
            
            try {
                observer.observerNotify();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }



    }
    
}
