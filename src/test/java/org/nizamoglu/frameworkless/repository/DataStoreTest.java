package org.nizamoglu.frameworkless.repository;

import org.nizamoglu.frameworkless.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataStoreTest {
    AccountRepository repository;

    @Before
    public void setup(){
        repository = new AccountRepository();
    }
    @Test
    public void should_save_data(){
        Account account = new Account("John Doe",0L);

        repository.save(account);

        Assert.assertNotNull(account.getId());
    }

    @Test
    public void should_fetch_data(){
        Account query = new Account("John Doe",0L);
        repository.save(query);

        Account account = repository.findById(query.getId());

        Assert.assertEquals(query.getId(),account.getId());
        Assert.assertEquals(query.getName(),account.getName());
        Assert.assertEquals(query.getBalance(),account.getBalance());
    }

    @Test
    public void should_return_null(){
        Account account = repository.findById(1000);

        Assert.assertNull(account);
    }

    @Test
    public void should_update_data(){
        Account account = insertData("John Doe",0L);
        account.setBalance(100L);

        repository.save(account);

        Account updated = repository.findById(account.getId());

        Assert.assertEquals((Long)100L,updated.getBalance());
        Assert.assertEquals(1,repository.size());
    }

    private Account insertData(String name,
                               long balance) {
        Account account = new Account(name,balance);
        repository.save(account);
        return account;
    }

}
