package printer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccie.IPrinter;

public class PrinterServer {


    public static void main(String[] args) {

 
        try {

            Registry registry = LocateRegistry.createRegistry(1099);

            IPrinter printer = new Printerimpl();

            registry.bind("Printer1",  printer);


            System.out.println("Remote obj set ......");
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
