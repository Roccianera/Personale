package disk;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener implements MessageListener{
    
    
    @Override
    public void onMessage(Message message) {

        MapMessage message2 = (MapMessage)message;
         Worker worker;
        try {

            int dato =message2.getInt("dato");
            int porta =message2.getInt("porta");

            worker = new Worker(dato, porta, new LoggerProxy(porta));
            worker.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }





}
