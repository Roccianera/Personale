package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Service.Calculator;

public class CalutlatorImplE extends UnicastRemoteObject implements Calculator {

    int cnt;

    protected CalutlatorImplE() throws RemoteException {
        super();
        cnt=0;
        
    }

    @Override
    public void SumValue(int sum) throws RemoteException {

        cnt+=sum;
    }

    @Override
    public int getValue() throws RemoteException {

        System.out.println("Value of Remote obj : " + cnt);

        return cnt;
    }

    
}
