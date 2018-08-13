/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver1;

import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static javax.imageio.ImageIO.read;


public class webserver1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        //Port is set to 10,000
        HttpServer server = HttpServer.create(new InetSocketAddress(10000), 0);
        server.createContext("/app", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

    }
    
}

class MyHandler implements HttpHandler {
    //@Override
    public void handle(HttpExchange t) throws IOException {
        
        //Information sent by the browser. Currently blank.
        InputStream is = t.getRequestBody();
        read(is); // .. read the request body
        
        //This is what is display in the browser
        String response = "This is the response";
        
        //This creates an HTTP response header, 200 means "Ok"
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    

}