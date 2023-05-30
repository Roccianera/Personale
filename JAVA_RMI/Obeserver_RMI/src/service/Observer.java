package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {

    /* callbackMethod  */

    public void observerNotify() throws RemoteException;
    
}
