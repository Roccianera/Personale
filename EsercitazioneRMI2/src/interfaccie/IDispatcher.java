package interfaccie;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDispatcher extends Remote {

    public void addPrinter(IPrinter printer , int PortNumber) throws RemoteException;
    public boolean printRequest(String docName  ) throws RemoteException;
    
}
