package dispatcher;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccie.IPrinter;

public class PrintThread extends Thread {



    private    String nomeDocumeto;
    private   String nomePrinter ;





    public  PrintThread (String nomeDocumento , String nomePrinter ){
        this.nomeDocumeto= nomeDocumento;
        this.nomePrinter = nomePrinter;
    }




    @Override
    public void run() {


        try {

            Registry registry = LocateRegistry.getRegistry();
            IPrinter printer = (IPrinter) registry.lookup(nomePrinter);
            printer.printDoc(nomeDocumeto);

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
}
