package org.nizamoglu.frameworkless.controller;

import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.model.Account;
import org.nizamoglu.frameworkless.service.TransferService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TransferControllerTest {

    @Before
    public void setup(){

    }

    @Test
    public void should_success(){
        TransferService transferServiceDummyMock = new TransferServiceDummyMock();
        TransferController controller = new TransferController(transferServiceDummyMock);
        TransferTransaction tx = new TransferTransaction(3,4,50L);

        RestResponse result = controller.transfer(tx);

        assertNotNull(result);
    }

    @Test
    public void should_transfer_money(){
        TransferServiceSuccessMock transferServiceSuccessMock = new TransferServiceSuccessMock();
        TransferController controller = new TransferController(transferServiceSuccessMock);
        TransferTransaction tx = new TransferTransaction(3,4,50L);

        RestResponse result = controller.transfer(tx);

        assertTrue(transferServiceSuccessMock.isTransferCalled());
        assertNotNull(result);
    }

    @Test
    public void should_transfer_money_with_correct_result_values(){
        TransferServiceSuccessMock transferServiceSuccessMock = new TransferServiceSuccessMock();
        TransferController controller = new TransferController(transferServiceSuccessMock);
        TransferTransaction tx = new TransferTransaction(3,4,50L);

        RestResponse result = controller.transfer(tx);

        assertEquals("Transfer Complete",result.getMessage());
    }

    @Test
    public void should_return_error(){
        TransferServiceErrorMock transferServiceErrorMock = new TransferServiceErrorMock();
        TransferController controller = new TransferController(transferServiceErrorMock);
        TransferTransaction tx = new TransferTransaction(3,4,50L);

        RestResponse result = controller.transfer(tx);

        assertEquals("Transfer Error",result.getMessage());
    }

    @Test
    public void should_call_service_fetch(){
        TransferServiceSuccessMock transferServiceSuccessMock = new TransferServiceSuccessMock();
        TransferController controller = new TransferController(transferServiceSuccessMock);
        Map<String,String> params = new HashMap<>();
        params.put("id","5");
        controller.fetch(params);

        assertTrue(transferServiceSuccessMock.isFetchCalled());
    }

    @Test
    public void should_call_service_create(){
        TransferServiceSuccessMock transferServiceSuccessMock = new TransferServiceSuccessMock();
        TransferController controller = new TransferController(transferServiceSuccessMock);

        controller.create(new Account("John Doe",50L));

        assertTrue(transferServiceSuccessMock.isCreateCalled());
    }
}
