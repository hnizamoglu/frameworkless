package org.nizamoglu.frameworkless.config;

import com.sun.net.httpserver.HttpHandler;
import org.nizamoglu.frameworkless.repository.AccountRepository;
import org.nizamoglu.frameworkless.controller.TransferController;
import org.nizamoglu.frameworkless.service.TransferService;
import org.nizamoglu.frameworkless.service.TransferServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class SimpleBeanFactory {

    private static SimpleBeanFactory instance = null;
    private AccountRepository repository = null;
    private TransferService transferService = null;
    private TransferController transferController = null;
    private Map<String,HttpHandler> handlers = null;

    public static SimpleBeanFactory getInstance() {
        if(instance == null){
            instance = new SimpleBeanFactory();
        }
        return instance;
    }

    public AccountRepository createAccountRepository() {
        if(repository == null){
            repository = new AccountRepository();
        }
        return repository;
    }

    public TransferService createTransferService() {
        if(transferService == null){
            transferService = new TransferServiceImpl(createAccountRepository());
        }
        return transferService;
    }

    public TransferController createTransferController() {
        if(transferController == null){
            transferController = new TransferController(createTransferService());
        }
        return transferController;
    }

    public Map<String,HttpHandler> createHandlers(){
        if(handlers == null){
            handlers = new HashMap<>();
        }
        handlers.put("/api/transfer",createTransferController());

        return handlers;
    }
}
