package org.nizamoglu.frameworkless.controller;

import org.nizamoglu.frameworkless.model.Account;
import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.service.TransferService;

public class TransferServiceDummyMock implements TransferService {
    @Override
    public void transfer(TransferTransaction tx) {

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
