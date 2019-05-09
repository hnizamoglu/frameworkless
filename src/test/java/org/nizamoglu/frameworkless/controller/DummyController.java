package org.nizamoglu.frameworkless.controller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;

public class DummyController extends ControllerBase {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }

    public <T> T readRequest(InputStream req, Class<T> clazz){
        return super.readRequest(req,clazz);
    }
}
