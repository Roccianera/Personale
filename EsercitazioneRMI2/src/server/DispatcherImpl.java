package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
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

    private void Callback(int PortNumber) throws IOException{

        Socket socket  = new Socket(InetAddress.getLocalHost(), PortNumber);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream.writeUTF("Aggiunto Printer");

        dataInputStream.readUTF();
        
        socket.close();


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
        printers.add(printer);
        System.out.println("Aggiunto printer con portNumeber  "+ PortNumber);
        
        

        try {
 
            Callback(PortNumber);



        } catch (Exception e) {
            System.out.println("Callback fallita ");
            e.printStackTrace();
        }











    }







}
