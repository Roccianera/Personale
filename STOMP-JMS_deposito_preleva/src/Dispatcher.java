


import javax.jms.*;
import javax.naming.*;

import java.util.Hashtable;





public class Dispatcher {



    public static void main(String[] args) {



        Hashtable<String,String> properties = new Hashtable<>();

        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("queue.request", "request");
		properties.put("queue.response", "response");


        try {
            

            Context context = new InitialContext(properties);

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");

            Queue queueRequest = (Queue)context.lookup("request");
            QueueConnection  queueConnection = queueConnectionFactory.createQueueConnection();

            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueReceiver receiver = queueSession.createBrowser(queueRequest);


            Queue queueResponse = (Queue) context.lookup("resposne");

            int port = Integer.valueOf(args[0]);

            DispatcherMsgListener listener = new DispatcherMsgListener(queueSession,queueResponse,port);

            System.out.println("Dispatcher avviato ");


        } catch (Exception e) {

            e.printStackTrace();
        }
		

        
    }
    
}
