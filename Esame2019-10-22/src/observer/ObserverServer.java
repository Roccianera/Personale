package observer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class ObserverServer {

    

    public static void main(String[] args) {

        if(args.length<2){

            System.out.println("BAD arguments ");
            return;
        }





        Registry registry;
        try {
            registry = LocateRegistry.getRegistry();
            IDispatcher dispatcher =(IDispatcher) registry.lookup("dispatcher");
            ObserverImpl observer = new ObserverImpl(args[1], dispatcher);
            dispatcher.attach(observer, args[0]);
            System.out.println("observer sottoscritto");
            
      
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
