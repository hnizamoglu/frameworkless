package org.nizamoglu.frameworkless.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class SuccessHttpHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200,0);
        httpExchange.close();
    }
}
