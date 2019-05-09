package org.nizamoglu.frameworkless.service;

import org.nizamoglu.frameworkless.exception.TransferParameterException;
import org.nizamoglu.frameworkless.model.Account;
import org.nizamoglu.frameworkless.repository.AccountRepository;
import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.exception.AccountNotFoundException;
import org.nizamoglu.frameworkless.exception.InsufficientBalanceException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TransferServiceTest {

    private AccountRepository repository;
    private TransferServiceImpl transferService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setup(){
        repository = new AccountRepository();
        transferService = new TransferServiceImpl(repository);
    }

    @Test
    public void should_transfer_money(){

        Account johns = new Account("John Doe",100L);
        Account janes = new Account("Jane Doe",100L);
        repository.save(johns);
        repository.save(janes);

        TransferTransaction tx = new TransferTransaction(johns.getId(),janes.getId(),30L);

        transferService.transfer(tx);

        Account updatedJohn = repository.findById(johns.getId());
        Account updatedJane = repository.findById(janes.getId());

        assertEquals((Long)70L,updatedJohn.getBalance());
        assertEquals((Long)130L,updatedJane.getBalance());
        assertEquals(2,repository.size());
    }

    @Test
    public void should_throw_account_not_found_exception(){
        exceptionRule.expect(AccountNotFoundException.class);
        exceptionRule.expectMessage("Account not found");

        TransferTransaction tx = new TransferTransaction(3,4,10L);
        transferService.transfer(tx);
    }

    @Test
    public void should_throw_insufficient_balance(){
        exceptionRule.expect(InsufficientBalanceException.class);
        exceptionRule.expectMessage("Not enough money");

        Account johns = new Account("John Doe",100L);
        Account janes = new Account("Jane Doe",100L);
        repository.save(johns);
        repository.save(janes);

        TransferTransaction tx = new TransferTransaction(johns.getId(),janes.getId(),150L);
        transferService.transfer(tx);
    }

    @Test
    public void should_fetch_data(){
        Account johns = new Account("John Doe", 50L);
        repository.save(johns);

        Account fetched = transferService.fetch(johns.getId());

        assertNotNull(fetched);
        assertEquals(johns.getId(),fetched.getId());
        assertEquals(1,repository.size());
    }

    @Test
    public void should_create_account(){
        Account johns = transferService.create("John Doe",50L);

        Account created = repository.findById(johns.getId());

        assertNotNull(created);
        assertEquals(johns.getId(),created.getId());
        assertEquals(1,repository.size());
    }

    @Test
    public void should_throw_parameter_exception(){
        exceptionRule.expect(TransferParameterException.class);
        exceptionRule.expectMessage("Sender and Receiver accounts cannot be the same");
        Account johns = new Account("John Doe",100L);
        repository.save(johns);

        transferService.transfer(new TransferTransaction(johns.getId(),johns.getId(),10L));
    }

    @Test
    public void should_throw_parameter_exception_when_amount_is_negative(){
        exceptionRule.expect(TransferParameterException.class);
        exceptionRule.expectMessage("Invalid amount to transfer");
        Account johns = new Account("John Doe",100L);
        Account janes = new Account("Jane Doe",100L);
        repository.save(johns);
        repository.save(janes);

        transferService.transfer(new TransferTransaction(johns.getId(),janes.getId(),-10L));
    }

}
