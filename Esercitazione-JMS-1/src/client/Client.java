package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.*;

public class Client {

    private static final int N = 10 ;
    public static void main(String[] args) throws NamingException, JMSException {

        Hashtable <String, String> p = new Hashtable <String, String>();
		
		p.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
		p.put("queue.Richiesta", "Richiesta");
		p.put("queue.Risposta", "Risposta");

        Context context = new InitialContext(p);
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
        Queue requestQueue = (Queue)context.lookup("Richiesta");
        Queue replyQueue = (Queue)context.lookup("Risposta");
        
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
        queueConnection.start();

        QueueSession queueSession = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);


        QueueReceiver receiver = queueSession.createReceiver(replyQueue);
        ClientListener listener = new ClientListener();
        receiver.setMessageListener(listener);

        QueueSender sender = queueSession.createSender(requestQueue);


        MapMessage message = queueSession.createMapMessage();
        
        for (int i = 0; i < N; i++) {
            if(Math.random()<0.5){

                message.setString("operazione", "deposita");
                Random r = new Random();
                int randomvalue = r.nextInt(100);
                message.setInt("valore",randomvalue);
                
                message.setJMSReplyTo(replyQueue);
                sender.send(message);

                System.out.println("[CLIENT] Mandato messaggio deposita con valore :" + randomvalue);


            }else{

                message.setString("operazione", "preleva");
                message.setJMSReplyTo(replyQueue);
                
                sender.send(message);
                
                System.out.println("[CLIENT] Mandato messaggio di preleva ");


            }
        }

        
        
    }
}
