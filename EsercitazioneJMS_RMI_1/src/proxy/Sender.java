package proxy;

import javax.jms.*;
import javax.naming.*;

import java.util.Hashtable;


public class Sender extends Thread {

	public  int message;

	public Sender( int message){
		this.message= message;
	}

	@Override
	public void run() {
		
		Hashtable<String, String> properties = new Hashtable<String,String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("queue.risposta", "risposta");

		try {
			Context context = new InitialContext(properties);
			QueueConnectionFactory  queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue risposta = (Queue)context.lookup("risposta");
			QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
	
			QueueSender sender = queueSession.createSender(risposta);
			MapMessage mapMessage= queueSession.createMapMessage();
			mapMessage.setInt("valore", message);

			sender.send(mapMessage);
	
		} catch (Exception e) {
			e.printStackTrace();
		}





		
	}

}
