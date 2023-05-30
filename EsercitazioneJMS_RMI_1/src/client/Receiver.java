package client;
import java.util.Hashtable;
import javax.jms.*;
import javax.naming.*;

public class Receiver extends Thread {
	
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
			

			QueueReceiver receiver = queueSession.createReceiver(risposta);

	
			MyListener listener = new MyListener();

			receiver.setMessageListener(listener);
			queueConnection.start();

			System.out.println("Listener in ascolto ......");


		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		





		
	}

}
