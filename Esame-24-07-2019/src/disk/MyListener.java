package disk;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener  implements MessageListener{


    @Override
    public void onMessage(Message message) {
        
        MapMessage mess = (MapMessage) message;

    
        System.out.println("Messaggio ricevuto ");
        WorkerThread thread = new WorkerThread(mess);

        thread.start();

        


    }
    
}
