package org.nizamoglu.frameworkless.service;

import org.junit.Before;
import org.junit.Test;
import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.model.Account;
import org.nizamoglu.frameworkless.repository.AccountRepository;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransferServiceMultiThreadingTest {

    AccountRepository repository;
    TransferService transferService;

    @Before
    public void setup(){
        repository = new AccountRepository();
        transferService = new TransferServiceImpl(repository);
    }

    @Test
    public void multiple_thread_test(){
        Account johns = new Account("John Doe", 100L);
        Account janes = new Account("Jane Doe",100L);
        repository.save(johns);
        repository.save(janes);

        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

        TransferTransaction johnToJane = new TransferTransaction(johns.getId(),janes.getId(),2L);
        TransferTransaction janeToJohn = new TransferTransaction(janes.getId(),johns.getId(),5L);

        for(int i = 0 ; i < 20 ; ++i){
            executor.submit(()-> transferService.transfer(johnToJane));
            executor.submit(()->transferService.transfer(janeToJohn));
        }

        try {
            executor.awaitTermination(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals((Long)160L,transferService.fetch(johns.getId()).getBalance());
        assertEquals((Long)40L,transferService.fetch(janes.getId()).getBalance());
    }

}
