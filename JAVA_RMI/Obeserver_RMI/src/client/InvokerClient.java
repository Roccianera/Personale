package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.MyService;

public class InvokerClient {

    public static void main ( String[] args ){
		try{
			
			
			
			/*
			 * 1-Ottiene il riferimento al servizio remoto
			 * 2-Crea l'oggetto callback (ObserverImpl)
			 * 3-Invoca il metodo remoto di MyService per registrare l'observer (attachObserver)
			 */
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			MyService stub_myservice = (MyService)rmiRegistry.lookup("myservice");
			System.out.println ("Got reference < myservice > " );
			System.out.println ( stub_myservice.toString() );
		
			stub_myservice.service_method();
					
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
    
}
