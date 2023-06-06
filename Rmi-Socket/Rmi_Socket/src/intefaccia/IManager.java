package intefaccia;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IManager extends Remote {



    public void subscribe(int compenentID, int porta) throws RemoteException;
    public void sendNotification(IAlertNotification notification) throws RemoteException;



    
}
