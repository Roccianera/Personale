package pubsubapp;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Publisher {

    public static void main(String[] args) {
        
        Hashtable<String,String> jndiProperties= new Hashtable<String,String>();
        jndiProperties.put( "java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		jndiProperties.put( "java.naming.provider.url", "tcp://127.0.0.1:61616" );
        
        jndiProperties.put("topic.test","mytesttopic");

        try {
            Context jndiContext = new InitialContext(jndiProperties);

            
            TopicConnectionFactory connectionFactory =(TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Topic topic =(Topic) jndiContext.lookup("test");

            TopicConnection topicConn =  connectionFactory.createTopicConnection();
            TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            TopicPublisher pub = topicSession.createPublisher(topic);
            TextMessage text = topicSession.createTextMessage();

            for (int i = 0; i < 5; i++) {
                    text.setText("hello_"+i);
                    pub.publish(text);
            }

            text.setText("fine");  
            pub.send( text);

            System.out.println("I messaggi sono stati inviati!");

            pub.close();
            topicSession.close();
            topicConn.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
