package client;

public class Client {
	
	public static void main(String[] args){
	
		Receiver threadReceiver = new Receiver();
		Sender threadSender = new Sender();
		
		threadReceiver.start();
		threadSender.start();
		
	}
	
	
}
