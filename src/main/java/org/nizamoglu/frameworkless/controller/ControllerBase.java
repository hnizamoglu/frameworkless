package org.nizamoglu.frameworkless.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ControllerBase implements HttpHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected <T> int writeResult(HttpExchange httpExchange, T result, int code) throws IOException {
        String str = objectMapper.writeValueAsString(result);
        byte[] bytes = str.getBytes();
        httpExchange.sendResponseHeaders(code,bytes.length);
        OutputStream out = httpExchange.getResponseBody();
        out.write(bytes);
        out.flush();
        return bytes.length;

    }

    protected <T> T readRequest(InputStream req, Class<T> clazz) {
        try{
            return objectMapper.readValue(req,clazz);
        } catch(Exception e){
            return null;
        }
    }

    public Map<String,String> parseRequestUrlParams(String query){
        if(query == null) return Collections.emptyMap();

        Map<String,String> ret = new HashMap<>();
        Arrays.stream(query.split("&"))
                .forEach(item->{
                    String [] qParams = item.split("=");
                    ret.put(qParams[0],qParams[1]);
                });
        return ret;

    }
}
