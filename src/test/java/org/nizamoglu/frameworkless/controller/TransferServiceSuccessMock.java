package org.nizamoglu.frameworkless.controller;

import org.nizamoglu.frameworkless.model.Account;
import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.service.TransferService;

public class TransferServiceSuccessMock implements TransferService {
    private boolean transferCalled = false;
    private boolean fetchCalled = false;
    private boolean createCalled = false;

    @Override
    public void transfer(TransferTransaction tx) {
        transferCalled = true;
    }

    @Override
    public Account create(String name, Long balance) {
        createCalled = true;
        return null;
    }

    @Override
    public Account fetch(int id) {
        fetchCalled = true;
        return null;
    }

    public boolean isTransferCalled() {
        return transferCalled;
    }

    public boolean isFetchCalled() {
        return fetchCalled;
    }

    public boolean isCreateCalled() {
        return createCalled;
    }
}
