package proxy;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccia.IService;

public class Proxy {
	
	
	public static void main(String[] args) {

		try {

			Registry registry = LocateRegistry.getRegistry();
			IService  service = (IService) registry.lookup("servizio");
	
			Receiver receiverThread= new Receiver(service);
		
			receiverThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

}
