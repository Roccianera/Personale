package generator;

import java.util.Random;

import intefaccia.IAlertNotification;
import intefaccia.IManager;

public class WorkerThread extends Thread{


    private IManager manager;

    public WorkerThread (IManager manager){

        this.manager=manager;
    }





    @Override
    public void run() {

        try {


            Random r = new Random();
            IAlertNotification notification= new AlertNotificationImpl(2, r.nextInt(3)+1);

            System.out.println("[Worker] Creata notifica con componentID"+notification.getComeponetID()+" e critical "+notification.getCriticaly());

            manager.sendNotification(notification);

            System.out.println("[Worker] notifican inviata ");



            
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }




    
}
