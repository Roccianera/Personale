package pubsubapp_asynch;


import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Publisher {
    

    public static void main(String[] args) throws NamingException, JMSException {
        
        
        String[] args1 = new String[2];

        args1[0] = "soccer";
        args1[1] = "Italy-wins";
        



        if(args1.length<2){
            System.err.println("Bad args");
            return;
        }

        Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("topic.soccer" , "soccernews");

        properties.put("topic.soccer", "soccernews");
        properties.put("topic.tennis", "tennisnews");
        
        Context jndiContext = new InitialContext(properties);
        Topic soccerTopic =(Topic) jndiContext.lookup("soccer");
        Topic tennisTopic = (Topic) jndiContext.lookup("tennis");

        TopicConnectionFactory tConnectionFactory = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");

        TopicConnection topicConnection =tConnectionFactory.createTopicConnection();
        TopicSession tSession = topicConnection.createTopicSession(false,   Session.AUTO_ACKNOWLEDGE);



        Topic  topic;

        if(args1[0].equalsIgnoreCase("soccer") )
            topic = soccerTopic;
        else if(args1[0].equalsIgnoreCase("tennis"))
            topic = tennisTopic;
        else{
            System.err.println("unknown topic ");
            return;
        }

        TopicPublisher publisher = tSession.createPublisher(topic);
        
        TextMessage message = tSession.createTextMessage(args1[1]);
        message.setIntProperty("propInt", 10); // can be used by possible message selectors by subscribers  

        publisher.publish(message);
        
        System.out.println("I've published the message" + args1[1]);

        //Some attribures 
        
        		//Some attributes of the message
		System.out.println("\nMessage id " + message.getJMSMessageID());
		System.out.println("Message id " + message.getJMSCorrelationID()); // not set
		System.out.println("Message id " + message.getJMSDeliveryMode()); //PERSISTENT, NON-PERSISTENT
		System.out.println("Message id " + message.getJMSExpiration());
		System.out.println("Message id " + message.getJMSPriority());
		System.out.println("Message id " + message.getJMSReplyTo());
		
		//Closing resources.
		publisher.close();
        tSession.close();
        topicConnection.close();


        


    }
}
