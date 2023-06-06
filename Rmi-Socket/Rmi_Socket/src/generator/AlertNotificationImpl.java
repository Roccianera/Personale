package generator;

import intefaccia.IAlertNotification;

public class AlertNotificationImpl implements IAlertNotification {



    private int compenentID ;
    private int criticaly;

    public AlertNotificationImpl(int compenentID, int criticaly){
        this.compenentID=compenentID;
        this.criticaly=criticaly;
    }







    @Override
    public int getComeponetID() {

        return compenentID;
  
    }

    @Override
    public int getCriticaly() {
        return criticaly;

    }

    
}
