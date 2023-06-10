package client;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;



public class Client {



    public static void main(String[] args) {


        if( args.length < 2){

            System.out.println("Bad arguments ");
            return;
        }






        int DATA = Integer.parseInt(args[0]);
        int PORT = Integer.parseInt(args[1]);





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

            QueueSender sender = queueSession.createSender(queue);

            MapMessage mapMessage = queueSession.createMapMessage();

            mapMessage.setInt("porta", PORT);
            mapMessage.setInt("dato", DATA);

            sender.send(mapMessage);

            System.out.println("Messaggio mandato ");




        } catch (Exception e) {
            e.printStackTrace();
        }   finally{


            //TODO clean


        }












        
    }
    
}
