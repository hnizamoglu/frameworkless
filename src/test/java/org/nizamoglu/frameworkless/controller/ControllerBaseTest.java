package org.nizamoglu.frameworkless.controller;

import org.nizamoglu.frameworkless.model.Account;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

import static org.junit.Assert.*;

public class ControllerBaseTest {

    @Test
    public void should_read_json_into_object(){
        String accountString = "{\"name\":\"John Doe\",\"balance\":400}";
        DummyController mockDummyController = new DummyController();
        InputStream inputStream = new ByteArrayInputStream(accountString.getBytes(Charset.forName("UTF-8")));

        Account account = mockDummyController.readRequest(inputStream,Account.class);

        assertNotNull(account);
        assertEquals("John Doe",account.getName());
        assertEquals((Long)400L,account.getBalance());

    }
    @Test
    public void should_parse_request_params(){
        String url = "name=John&surname=Doe";
        DummyController mockDummyController = new DummyController();

        Map<String,String> params = mockDummyController.parseRequestUrlParams(url);

        assertEquals("John",params.get("name"));
        assertEquals("Doe",params.get("surname"));
    }
}
