package org.nizamoglu.frameworkless.service;

import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.model.Account;

public interface TransferService {
    void transfer(TransferTransaction tx);
    Account create(String name, Long balance);
    Account fetch(int id);
}
