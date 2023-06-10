package observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IDispatcher;
import interfaces.IObserver;
import interfaces.IReading;

public class ObserverImpl extends UnicastRemoteObject implements IObserver {


    private String file;
    private IDispatcher dispatcher;

    public ObserverImpl(String file , IDispatcher dispatcher) throws RemoteException{
        this.file=file;
        this.dispatcher= dispatcher;
    }


    @Override
    public void notifyReading() throws RemoteException {
        


        
        try {
            IReading reading = dispatcher.getReading();
            
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter  bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);


            printWriter.println("Lettura :  "+Integer.toString(reading.getValue()));

            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();






        } catch (Exception e) {
            e.printStackTrace();
        }















    }













    
}
