package Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Service.Calculator;

public class Client {


    public static void main(String[] args) {
    try {
        Registry registry = LocateRegistry.getRegistry();

        Calculator calc =(Calculator) registry.lookup("Calculator");
        
        calc.SumValue(5);

        int x =calc.getValue();

        System.out.println("Try : "+ x);





    } catch (RemoteException e) {

        e.printStackTrace();
    } catch (NotBoundException e) {
        e.printStackTrace();
    }
        
    }


    



    






    
}
