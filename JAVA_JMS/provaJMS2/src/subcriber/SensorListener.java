package subcriber;


import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import coda.Coda;

public class SensorListener implements MessageListener {
    // inserire in un file i commandi che arrivano 


    private Coda coda;

    public SensorListener (Coda coda ){
        this.coda = coda;
    }




    @Override
    public void onMessage(Message message) {

        try {

            TextMessage textMessage = (TextMessage) message;
    
            System.out.println("[SENSOR LISTENER] Comando ricevuto : "+  textMessage.getText());


            TManager threadManager= new TManager(coda,textMessage.getText());

            threadManager.start();


            
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    
}
