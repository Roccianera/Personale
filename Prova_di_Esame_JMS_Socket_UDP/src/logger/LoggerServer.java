package logger;

public class LoggerServer {

    public static void main(String[] args) {


        LoggerSkelE loggerSkelE = new LoggerImpl(3000);
        loggerSkelE.runSkeleton();



    }
    
}
