package pubsubapp_asynch;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Subscriber {
    

    public static void main(String[] args) throws NamingException, JMSException {

        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");	
        properties.put("topic.soccer" , "soccernews"); //consumes from the topic soccernews 
        Context jndiContext = new InitialContext(properties);
    
        TopicConnectionFactory tcf= (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
        Topic soccer = (Topic) jndiContext.lookup("soccer");
    
        TopicConnection  topicConnection = tcf.createTopicConnection();
        
        TopicSession ts = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);

        TopicSubscriber  subscriber = ts.createSubscriber(soccer,"propInt=10",false);

        // creo un message listener per attivare la ricezione assincrona 

        MyListener msgl = new MyListener();
        subscriber.setMessageListener(msgl);

        topicConnection.start();

        System.out.println("Listener set ....");

        
    }


    





}
