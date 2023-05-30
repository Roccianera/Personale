package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LetturaDocWeb {




    public static void main(String[] args) {

        try {
            
            URL page= new URL("http://www.scuolapsb.unina.it/");
            URLConnection conn = page.openConnection();
            conn.connect();

            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // sta leggendo HTML
            String line = read.readLine();
            while(line!=null){
                System.out.println(line);
                line=read.readLine();
            }

        }   catch( MalformedURLException e){
            e.printStackTrace();

        }   catch( IOException e ){
            e.printStackTrace();
        }


    }
    
}
