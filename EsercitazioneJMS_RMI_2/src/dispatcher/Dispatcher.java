package dispatcher;
import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;


public class Dispatcher {

    public static void main(String[] args) {

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
            TopicSubscriber subscriber = topicSession.createSubscriber(topic);
            MyListener listener =  new MyListener();
            
            subscriber.setMessageListener(listener);
            topicConnection.start();

            System.out.println("Listener set.......");

    
            
        } catch (Exception e) {
            e.printStackTrace();
        }    

    
    
    }
    
}
