package logger;


import interfaccia.ILogger;

public class LoggerServer  {

    public static void main(String[] args) {

        int PORT = Integer.parseInt(args[0]);


        LoggerSkeleton loggerSkeleton = new LoggerSkeleton(new LoggerImpl(), PORT);

        loggerSkeleton.run_skeleton();
        
    
    }

 

    
}
