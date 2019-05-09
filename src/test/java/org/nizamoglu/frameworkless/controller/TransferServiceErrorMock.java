package org.nizamoglu.frameworkless.controller;

import org.nizamoglu.frameworkless.model.Account;
import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.exception.AccountNotFoundException;
import org.nizamoglu.frameworkless.service.TransferService;

public class TransferServiceErrorMock implements TransferService {
    @Override
    public void transfer(TransferTransaction tx) {
        throw new AccountNotFoundException("Transfer Error");
    }

    @Override
    public Account create(String name, Long balance) {
        return null;
    }

    @Override
    public Account fetch(int id) {
        return null;
    }
}
