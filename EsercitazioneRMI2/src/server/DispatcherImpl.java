package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


import interfaccie.IDispatcher;
import interfaccie.IPrinter;


public class DispatcherImpl  extends UnicastRemoteObject implements IDispatcher{
    
	private static final long serialVersionUID = -2L;

    Vector<IPrinter> printers;
    //Vector<Integer> ports;
  //  private IPrinter  proxyPrinter;

    public DispatcherImpl() throws RemoteException{

        printers = new Vector<IPrinter>();
      //  ports = new Vector<Integer>();
  //      this.proxyPrinter =proxyPrinter;
    }

    @Override
    public boolean printRequest(String docName) throws RemoteException {      
       


        for (IPrinter iPrinter : printers) {
            
            if(iPrinter.print(docName)) 
                return true;

        }

        return false;


        






    }

    @Override
    public void addPrinter(IPrinter printer, int PortNumber) throws RemoteException {
   
       // ports.add(PortNumber);

        printers.add(new Printerproxy(printer,PortNumber));
        System.out.println("Aggiunto printer con portNumeber  "+ PortNumber);
        
    }







}
