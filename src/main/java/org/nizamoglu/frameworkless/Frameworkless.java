package org.nizamoglu.frameworkless;

import org.nizamoglu.frameworkless.config.SimpleBeanFactory;

import java.io.IOException;

public class Frameworkless {
    public static final int PORT = 8080;
    public static void main(String []args) throws IOException {
        SimpleBeanFactory factory = SimpleBeanFactory.getInstance();
        CustomServer server = new CustomServer(PORT,factory.createHandlers());
        server.start();
    }
}
