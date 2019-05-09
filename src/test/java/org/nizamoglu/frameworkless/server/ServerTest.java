package org.nizamoglu.frameworkless.server;

import com.sun.net.httpserver.HttpHandler;
import org.nizamoglu.frameworkless.CustomServer;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class ServerTest {

    private static final int PORT = 8123;

    @Test
    public void should_listen_multiple_endpoints() throws IOException {
        Map<String,HttpHandler> mockControllers = createMockControllers();

        CustomServer server = new CustomServer(PORT,mockControllers);
        server.start();

        mockControllers.keySet().forEach(key->{
            try{
                URL url = new URL("http://localhost:"+PORT+key);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                assertEquals(200,conn.getResponseCode());
            } catch(Exception e){
                fail();
            }
        });
    }

    private Map<String, HttpHandler> createMockControllers() {
        Map<String,HttpHandler> controllers = new HashMap<>();

        controllers.put("/api/a",new SuccessHttpHandler());
        controllers.put("/api/b",new SuccessHttpHandler());
        controllers.put("/api/c",new SuccessHttpHandler());
        return controllers;
    }
}
