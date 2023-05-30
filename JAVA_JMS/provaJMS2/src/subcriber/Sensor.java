package subcriber;

import javax.naming.*;

import coda.Coda;
import codaimpl.CodaCircolare;
import codaimpl.CodaWrapperLock;

import javax.jms.*;
import java.util.Hashtable;

public class Sensor {

    public static final int D = 5;



public static void main(String[] args) throws NamingException, JMSException {
    
    Hashtable <String, String> prop = new Hashtable <String, String>();
		
    prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
    prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
     
    prop.put("topic.station", "station");

    Context context = new InitialContext(prop);

    TopicConnectionFactory topicConnectionFactory =  (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
    Topic stationTopic = (Topic)context.lookup("station");
    
    TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();

    TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    
    TopicSubscriber stationSubscriber= topicSession.createSubscriber(stationTopic);


    Coda coda = new CodaWrapperLock(new CodaCircolare(D));

    TExecutor threadExecutor = new TExecutor(coda);
    threadExecutor.start();

    SensorListener listener = new SensorListener(coda);

    stationSubscriber.setMessageListener(listener);

    topicConnection.start();

    System.out.println("Listener set ......");


    



}







    
}
