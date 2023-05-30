package client;
import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;
import java.util.Random;

public class Client {

    public static final int N_MESSAGGI =5;

    public static void main(String[] args) {
        
        if(args.length<1) return;

        String myPrinter = args[0];
        
        
		Hashtable<String, String> properties = new Hashtable<String,String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        properties.put("topic.PrintRequest", "PrintRequest"); 

        try {
            
            Context context = new InitialContext(properties);
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
            Topic topic = (Topic) context.lookup("PrintRequest");
            
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
            TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            
            TopicPublisher publisher = topicSession.createPublisher(topic);
            MapMessage mapMessage = topicSession.createMapMessage();
            mapMessage.setString("nomePrinter", myPrinter);


            for (int i = 0; i < N_MESSAGGI; i++) {

                Random rand = new Random();
                Integer randomValue= rand.nextInt(41);
                String nomeDocumento = new String("doc"+randomValue.toString());
                mapMessage.setString("nomeDocumento", nomeDocumento);

                publisher.publish(mapMessage);
                
            }



        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    
}
