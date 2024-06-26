package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.Hello;

public class HelloClient {
    private HelloClient () {};


    public static void main(String[] args) {
        // String host =(args.lenght<1 )? null : args[0];
        try {
            
            // ottiene il riferimento all ' oggetto Hello remoto 
            /* specificare l 'host' per fare il look up 
             * 
             * Registry registry =LocateRegistry.getRegistry(host);
             */

             Registry registry =LocateRegistry.getRegistry();
             Hello stub = (Hello) registry.lookup("Hello");

             //invocazione del metodo sayHello()
             String response =stub.sayHello();
             System.out.println("response : "+response);


        } catch (Exception e) {
            System.err.println("Client exception : "+ e.toString());
            
        }
        
    }
}
