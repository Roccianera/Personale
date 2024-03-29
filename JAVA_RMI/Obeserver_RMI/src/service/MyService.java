package service;

import java.rmi.Remote;
import java.rmi.RemoteException;


/*
 * interface MyService, espone un metodo per la gestione della callback distribuita
 * e un metodo legato al servizio remoto vero e proprio
 */


public interface MyService extends Remote {

    /*
     * Metodi per consentire l ' attach' di un observer
     * sul subject (Partecipanti del design Pattern Observer )
     */

    public void attachObserver(Observer observer) throws RemoteException;



    public void service_method() throws RemoteException;
    
}
