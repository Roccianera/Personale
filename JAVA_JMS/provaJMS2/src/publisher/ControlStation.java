package publisher;

import javax.naming.*;
import javax.jms.*;
import java.util.Hashtable;
import java.util.Random;

public class ControlStation {

    public static final int N=20;
    public static final int FIRST_CHOICE = 0 ;
    public static final  int SECOND_CHOICE = 1 ;
    public static final  int THIRD_CHOICE = 2 ;



    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
        /*
         * THIS CLASS GENERATES N MESS WHICH ARE THEN SENT TO THE TOPIC 
         */

        Hashtable <String, String> prop = new Hashtable <String, String>();
		
        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
         
        prop.put("topic.station", "station");

        Context context = new InitialContext(prop);

        TopicConnectionFactory topicConnectionFactory =  (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
        Topic stationTopic = (Topic)context.lookup("station");
        
        TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();

        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
        TopicPublisher publisherStation =  topicSession.createPublisher(stationTopic);

        TextMessage textMessage = topicSession.createTextMessage();

        Random r = new Random();

        for (int i = 0; i < N; i++) {


            int choice = r.nextInt(3);

            if (choice ==FIRST_CHOICE)
                textMessage.setText("startSensor");
            else if( choice == SECOND_CHOICE)
                textMessage.setText("stopSensor");
            else
                textMessage.setText("read");

            publisherStation.send(textMessage);
    
            System.out.println("Messaggio inviato");
            Thread.sleep(1000);
            
        }

        






        
    }
    
}
