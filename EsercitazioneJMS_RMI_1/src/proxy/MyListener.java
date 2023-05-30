package proxy;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import interfaccia.IService;


public class MyListener implements MessageListener {
	
	private IService service;
	public MyListener(IService service){
		this.service = service;
	}

	@Override
	public void onMessage(Message arg0) {

		MapMessage message  = (MapMessage) arg0;

		try {
			String operazione = message.getString("operazione");

			if(operazione.compareTo("deposita")==0)
				service.deposita(message.getInt("id_articolo"));
			else if(operazione.compareTo("preleva")==0){
		
				Sender  sender = new Sender(service.preleva());
				sender.start();
				
			} else{

				System.err.println("BAD ARGUMENT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
