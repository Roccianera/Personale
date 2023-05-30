package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccia.IService;

public class Server {

	public static void main(String[] args) {


		try {

			Registry registry = LocateRegistry.createRegistry(1099);
		
			IService service = new Service();

			registry.bind("servizio", service);

			System.out.println("Oggetto remoto messo a disposizione ......");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
