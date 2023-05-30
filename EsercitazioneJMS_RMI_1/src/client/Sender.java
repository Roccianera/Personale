package client;
import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.*;

public class Sender extends Thread {
	
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
			

			QueueSender sender = queueSession.createSender(richiesta);

			MapMessage mapMessage = queueSession.createMapMessage();



			for (int i = 0; i < 10; i++) {

				if(i%2==0){

					Random random = new Random();
					int id_articolo= random.nextInt(100);


					mapMessage.setInt("id_articolo", id_articolo);

					
					mapMessage.setString("operazione", "deposita");

					System.out.println("[CLIENT-SENDER]  deposita : " + id_articolo);


				}	else{

					mapMessage.setString("operazione", "preleva");
					System.out.println("[CLIENT-SENDER]  prelievo ");

				}

				sender.send(mapMessage);
			
			}



		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		

	






		
		
	}

}
