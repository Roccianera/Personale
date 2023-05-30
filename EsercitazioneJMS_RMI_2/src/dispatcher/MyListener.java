package dispatcher;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

        MapMessage mapMessage = (MapMessage) message;

        try {
            String nomeDocumento = mapMessage.getString("nomeDocumento");
            String nomePrinter = mapMessage.getString("nomePrinter");
            
            PrintThread printThread = new PrintThread(nomeDocumento, nomePrinter);

            printThread.start();
    
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    
}
