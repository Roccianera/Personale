package whiteboard;

import java.rmi.*;
import java.util.*;

public interface WhiteBoard  extends Remote{

    public void addShape(Shape s ) throws RemoteException;


    public void attachObserver(Observer observer)  throws RemoteException;


    public Vector<Shape> getShapes() throws RemoteException;
    
}
