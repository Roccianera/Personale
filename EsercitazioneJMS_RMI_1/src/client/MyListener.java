package client;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener implements MessageListener {
	
	@Override
	public void onMessage(Message arg0) {


		MapMessage message = (MapMessage)  arg0;


		try {
			System.out.println("[CLIENT-RECIVER] Elemento ricevuto : " +message.getInt("valore"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
