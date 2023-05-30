package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import whiteboard.Observer;
import whiteboard.Shape;
import whiteboard.WhiteBoard;

public class ObserverImpl extends UnicastRemoteObject  implements  Observer  {


    private static final long serialVersionUID = 1L;
    private WhiteBoard remoteWhiteBoard;

    public ObserverImpl(WhiteBoard w) throws RemoteException{
        remoteWhiteBoard= w;

    }





    @Override
    public void observerNotify() throws RemoteException {

        System.out.println("AddShapeInvoked()....");
        Vector<Shape> v = remoteWhiteBoard.getShapes();
        
        for (Shape shape : v) {
            shape.draw(); // Attenzione
        }
    }

    
}
