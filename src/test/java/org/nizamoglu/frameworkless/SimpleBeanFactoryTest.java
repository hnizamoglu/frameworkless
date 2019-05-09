package org.nizamoglu.frameworkless;


import org.nizamoglu.frameworkless.config.SimpleBeanFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBeanFactoryTest {
    private SimpleBeanFactory factory;

    @Before
    public void setup(){
        factory = SimpleBeanFactory.getInstance();
    }

    @Test
    public void should_return_repository_not_null(){
        assertNotNull(factory.createAccountRepository());
    }

    @Test
    public void should_return_service_not_null(){
        assertNotNull(factory.createTransferService());
    }

    @Test
    public void should_return_controller_not_null(){
        assertNotNull(factory.createTransferController());
    }
}
