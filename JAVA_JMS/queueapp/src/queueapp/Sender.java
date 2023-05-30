package queueapp;

import java.util.Hashtable;


import javax.jms.*;
import javax.naming.*;

public class Sender {
    

    public static void main(String[] args) {
        
        Hashtable<String , String> prop = new Hashtable<String, String>();

        		
		prop.put( "java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		prop.put( "java.naming.provider.url", "tcp://127.0.0.1:61616" );

        		//		jndi queue name   queue-name
		prop.put( "queue.test", "mytestqueue" );


        try {
            Context jndiContext =  new InitialContext(prop);

            //Lookuop administred obj 

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");

            Queue queue = (Queue) jndiContext.lookup("test"); // il prefisso "queue. " non fa parte del nome jndi 
            
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE );

            QueueSender sender = queueSession.createSender(queue);
            TextMessage message = queueSession.createTextMessage();

            for (int i = 0; i < 5; i++) {
                message.setText("hello_ "+ i);
                sender.send(message);
                Thread.sleep(1000);
            
            }

            message.setText("fine");
            sender.send(message);

            System.out.println("I messagi sono stati inviati ");


            sender.close();
            queueSession.close();
            queueConnection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
		

    }
}
