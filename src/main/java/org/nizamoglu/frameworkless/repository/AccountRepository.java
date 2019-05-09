package org.nizamoglu.frameworkless.repository;

import org.nizamoglu.frameworkless.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    private Integer counter = 0;
    private Map<Integer, Account> storage;

    public AccountRepository() {
        storage = new HashMap<>();
    }

    public Account save(Account account) {
        if(account.getId() != null){
            update(account);
            return account;
        }
        account.setId(counter++);
        storage.put(account.getId(),account);
        return account;
    }

    private void update(Account account) {
        Account existing = storage.get(account.getId());
        if(existing != null){
            existing.setBalance(account.getBalance());
        }
    }

    public Account findById(Integer id) {
        return storage.get(id);
    }

    public int size() {
        return storage.size();
    }
}
