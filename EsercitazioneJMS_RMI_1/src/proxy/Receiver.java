package proxy;

import interfaccia.IService;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;


public class Receiver extends Thread {

	private IService service ;
	public Receiver(IService service){
		this.service= service;
	}
	
	@Override
	public void run() {		

		Hashtable<String, String> properties = new Hashtable<String,String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("queue.richiesta", "richiesta");

		try {
			


			Context context = new InitialContext(properties);
			QueueConnectionFactory  queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue richiesta = (Queue)context.lookup("richiesta");
			QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = queueSession.createReceiver(richiesta);


			MyListener listener = new MyListener(service);
			receiver.setMessageListener(listener);
			queueConnection.start();

			System.out.println("[PROXY-RECEIVER] Listener in ascolto ......");






		} catch (Exception e) {
			e.printStackTrace();
		}



		
		




		
			
	}
	
}
