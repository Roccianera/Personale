package printer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccie.IDispatcher;
import interfaccie.IPrinter;

public class PrinterServer {


    public static void main(String[] args) {
        

        int port = Integer.parseInt(args[0]);
        String filepath = args[1];





        try {
            Registry registry;
            registry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = (IDispatcher) registry.lookup("gestore");

            IPrinter printer = new PrinterImpl(filepath);
            PrinterSkel printerSkel = new PrinterSkel(dispatcher, printer,  port );
            printerSkel.runSkeleton();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    
}
