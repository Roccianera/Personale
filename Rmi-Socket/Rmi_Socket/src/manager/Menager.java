package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import intefaccia.IAlertNotification;
import intefaccia.IManager;

public class Menager extends UnicastRemoteObject implements IManager {


   private  ArrayList<ArrayList<Integer>> topics;


   public Menager() throws RemoteException{

            topics= new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                topics.add(new ArrayList<Integer>());
            }
        

   }




    @Override
    public void subscribe(int compenentID, int porta) throws RemoteException {

            (topics.get(compenentID-1)).add(porta);
            System.out.println("[Menager] aggiunto subcriber "+ porta+" nel topic "+compenentID);

        
    }

    @Override
    public synchronized void  sendNotification(IAlertNotification notification) throws RemoteException {
        
        int compID =notification.getComeponetID();
        int critic= notification.getCriticaly();

        try {

            for (Integer port : topics.get(compID-1)) {
            
                SubscriberProxy proxy = new SubscriberProxy(port, compID-1);
                proxy.NotifyAlert(critic);
                
            }



        } catch (Exception e) {
            e.printStackTrace();
        }







    }
    
}
