package disk;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;


public class Disk {




    public static void main(String[] args) {
        


        Hashtable<String, String> prop = new Hashtable<>();



        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
        prop.put("queue.disk","disk");


        try {
            
            
            Context context = new InitialContext(prop);

            QueueConnectionFactory queueConnectionFactory =(QueueConnectionFactory) context.lookup("QueueConnectionFactory");
        
            Queue queue = (Queue) context.lookup("disk");

            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueReceiver reciver = queueSession.createReceiver(queue);

            reciver.setMessageListener(new MyListener());


            queueConnection.start();





        } catch (Exception e) {
            e.printStackTrace();
        }   finally{


           


        }






    }


    
}
