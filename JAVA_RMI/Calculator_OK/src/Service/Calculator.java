package Service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {


    public void SumValue(int sum ) throws RemoteException;
    public int getValue() throws RemoteException;
    
}
