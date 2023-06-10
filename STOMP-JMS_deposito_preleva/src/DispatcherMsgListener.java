import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import org.apache.activemq.broker.region.Queue;

public class DispatcherMsgListener implements MessageListener {


    private QueueSession  queueSession;

    private Queue  queueResponse;

    private int port ;


    public DispatcherMsgListener (QueueSession queueSession , Queue queueResponse, int port){
        this.port =port;
        this.queueResponse= queueResponse;
        this.queueSession = queueSession;
    }






    @Override
    public void onMessage(Message arg0) {
        
        TextMessage msg = (TextMessage) arg0;


        try {
            
            String message = msg.getText();
            IDispatcher dispatcher =







        } catch (Exception e) {
            // TODO: handle exception
        }





        
    }




    
}
