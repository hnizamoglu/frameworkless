package org.nizamoglu.frameworkless;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

public class CustomServer {

    private int port;
    private Map<String, HttpHandler> handlers;

    public CustomServer(int port, Map<String, HttpHandler> handlers) {
        this.port = port;
        this.handlers = handlers;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port),0);
        createContexts(server);
        server.setExecutor(null);
        server.start();
    }

    private void createContexts(HttpServer server) {
        handlers.keySet().forEach(key -> server.createContext(key,handlers.get(key)));
    }
}
