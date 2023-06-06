package printer;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

import interfaccie.IDispatcher;
import interfaccie.IPrinter;

public class PrinterSkel implements IPrinter {


    private IDispatcher dispatcher;
    private IPrinter printer;
    private int port ;



    public  PrinterSkel (IDispatcher dispatcher , IPrinter printer, int port){

        this.printer = printer;
        this.dispatcher =dispatcher;
        this.port =port;
    }



    public void runSkeleton(){

        try {
            
            
            dispatcher.addPrinter(printer, port);
            ServerSocket serverSocket = new ServerSocket(port);
            
            while (true) {

                Socket socket = serverSocket.accept();
                Worker worker = new Worker(socket,this);
                worker.run();
            }

       

        } catch (Exception e) {
            System.out.println("callback fallita");
            e.printStackTrace();
        }











    }


    @Override
    public boolean print(String docName) throws RemoteException {
        return printer.print(docName);
    }




    
}
