package pubsubapp;

import javax.jms.*;

import javax.naming.*;
import java.util.Hashtable;

public class Subscriber {


    public static void main(String[] args) {
        
        Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		
		jndiProperties.put( "java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		jndiProperties.put( "java.naming.provider.url", "tcp://127.0.0.1:61616" );
		
		//		jndi topic name   queue-name
		jndiProperties.put( "topic.test", "mytesttopic" );


        try {
            Context jndiContext = new InitialContext(jndiProperties);
            TopicConnectionFactory connectionFactory = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Topic topic = (Topic)jndiContext.lookup("test");

            TopicConnection topicConnection= connectionFactory.createTopicConnection();

            topicConnection.setClientID("clientDurable");

            topicConnection.start();

            TopicSession topicSession = topicConnection.createTopicSession(false,   Session.AUTO_ACKNOWLEDGE);

            TopicSubscriber sub = topicSession.createDurableSubscriber(topic,"DurableSub");



            TextMessage msg;


            do {
                System.out.println("In attessa di messagi !");
                msg =(TextMessage) sub.receive();
                
            } while (msg.getText().compareTo("fine")!= 0);

            sub.close();

            topicSession.close();
            topicConnection.close();
            

        } catch (Exception e) {
            e.printStackTrace();
        }






    }
    
}
