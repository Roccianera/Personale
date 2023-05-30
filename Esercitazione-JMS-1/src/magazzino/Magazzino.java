package magazzino;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;
import coda.Coda;
import codaimpl.CodaCircolare;
import codaimpl.CodaWrapperLock;


public class Magazzino {

    public static void main(String[] args) {

        Hashtable <String, String> p = new Hashtable <String, String>();
		
		p.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
		p.put("queue.Richiesta", "Richiesta");


        try {
            
            Context  context= new InitialContext(p);
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");

            Queue requestQueue =(Queue) context.lookup("Richiesta");
            
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver queueReceiver = queueSession.createReceiver(requestQueue);


            Coda coda =  new CodaWrapperLock(new CodaCircolare(5));



            MagazzinoListener listener= new MagazzinoListener(coda, queueConnection);
            
            queueReceiver.setMessageListener(listener);

            System.out.println("[MAGAZZINO] Server avviato ");




        } catch (NamingException e) {
            e.printStackTrace();
        }catch(JMSException e){
            e.printStackTrace();
        }






        
    }

}
