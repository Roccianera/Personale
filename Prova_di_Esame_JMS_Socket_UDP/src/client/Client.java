package client;

import java.util.Hashtable;

import javax.naming.*;
import javax.jms.*;


/**
 * Client
 */
public class Client {

    public static void main(String[] args) {
        
        if(args.length< 2){
            System.err.println(" Numero di argomenti insufficienti ");
            return;
        }

        int dato = Integer.parseInt(args[0]);
        int porta= Integer.parseInt(args[1]);

        
        Hashtable <String, String> p = new Hashtable <String, String>();
		
		p.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        p.put("topic.Disk", "Disk");

        try {
            Context context = new InitialContext(p);
            
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
            Topic diskTopic = (Topic)context.lookup("Disk");

            TopicConnection connection = topicConnectionFactory.createTopicConnection();    
            TopicSession session =connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            TopicPublisher publisher = session.createPublisher(diskTopic);
            MapMessage mapMessage =session.createMapMessage();

            mapMessage.setInt("porta", porta);
            mapMessage.setInt("dato", dato);

            publisher.send(mapMessage);
            

        } catch (Exception e) {
            e.printStackTrace();
        }


        








    }

    
}