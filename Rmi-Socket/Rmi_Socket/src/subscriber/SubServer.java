package subscriber;

public class SubServer {


    public static void main(String[] args) {




        SubscriberSkeleton skeleton = new SubscriberSkeleton(new SubscriberImpl(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
       
        skeleton.runSkeleton();



        
    }
    
}
