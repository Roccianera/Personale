package disk;
import java.util.Hashtable;

import javax.naming.*;
import javax.jms.*;
public class Disk {


    public static void main(String[] args) {


                
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

            TopicSubscriber subscriber = session.createSubscriber(diskTopic);
            MyListener listener = new MyListener();
            subscriber.setMessageListener(listener);
            connection.start();

        } catch (Exception e) {
            e.printStackTrace();
        }




        
    }
    
}
