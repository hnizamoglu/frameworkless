package org.nizamoglu.frameworkless.service;

import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.exception.AccountNotFoundException;
import org.nizamoglu.frameworkless.exception.InsufficientBalanceException;
import org.nizamoglu.frameworkless.exception.TransferParameterException;
import org.nizamoglu.frameworkless.model.Account;
import org.nizamoglu.frameworkless.repository.AccountRepository;

public class TransferServiceImpl implements TransferService {
    private AccountRepository accountRepository;

    public TransferServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public synchronized void transfer(TransferTransaction tx) {
        // todo: should extend thread safety. this will suffice for now, but it will create a bottleneck.
        Account sender = accountRepository.findById(tx.getSender());
        Account receiver = accountRepository.findById(tx.getReceiver());

        checkValidity(sender,receiver,tx);

        sender.setBalance(sender.getBalance()-tx.getAmount());
        receiver.setBalance(receiver.getBalance()+tx.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }

    private void checkValidity(Account sender, Account receiver, TransferTransaction tx) {
        if(sender == null || receiver == null){
            throw new AccountNotFoundException("Account not found");
        }

        if(tx.getAmount() > sender.getBalance()){
            throw new InsufficientBalanceException("Not enough money");
        }

        if(sender == receiver){
            // dangerous
            throw new TransferParameterException("Sender and Receiver accounts cannot be the same");
        }

        if(tx.getAmount() <= 0){
            // dangerous
            throw new TransferParameterException("Invalid amount to transfer");
        }
    }

    @Override
    public Account create(String name, Long balance) {
        return accountRepository.save(new Account(name,balance));
    }

    @Override
    public Account fetch(int id) {
        Account account = accountRepository.findById(id);
        if(account == null){
            throw new AccountNotFoundException("Account not found");
        }
        return account.clone();
    }
}
