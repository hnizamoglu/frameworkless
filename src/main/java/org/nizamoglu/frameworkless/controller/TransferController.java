package org.nizamoglu.frameworkless.controller;

import com.sun.net.httpserver.HttpExchange;
import org.nizamoglu.frameworkless.service.TransferService;
import org.nizamoglu.frameworkless.controller.dto.TransferTransaction;
import org.nizamoglu.frameworkless.exception.AccountNotFoundException;
import org.nizamoglu.frameworkless.exception.InsufficientBalanceException;
import org.nizamoglu.frameworkless.model.Account;

import java.io.IOException;
import java.util.Map;

public class TransferController extends ControllerBase {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    public RestResponse transfer(TransferTransaction tx) {
        try{
            transferService.transfer(tx);
        } catch(AccountNotFoundException | InsufficientBalanceException e){
            return new RestResponse(e.getMessage());
        }

        // todo : i18n
        return new RestResponse("Transfer Complete");
    }

    public RestResponse fetch(Map<String, String> params) {
        RestResponse resp;
        try{
            String idString = params.get("id");
            int id = Integer.parseInt(idString);
            Account account = transferService.fetch(id);
            resp = new RestResponse(account,"OK");
        } catch (AccountNotFoundException e){
            resp = new RestResponse(e.getMessage());
        } catch (NumberFormatException e){
            resp = new RestResponse("Incorrect Parameter ID");
        }
        return resp;
    }

    public RestResponse create(Account account) {
        Account created = transferService.create(account.getName(),account.getBalance());

        return new RestResponse(created,"Account Created");
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        switch (httpExchange.getRequestMethod()){
            case "POST":
                handlePost(httpExchange);
                break;
            case "GET":
                handleGet(httpExchange);
                break;
            default:
                httpExchange.sendResponseHeaders(405,0);
        }
        httpExchange.close();
    }

    private void handleGet(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getRawQuery();
        Map<String,String> queryMap = parseRequestUrlParams(query);

        RestResponse resp = fetch(queryMap);
        writeResult(httpExchange,resp,200);

    }

    private void handlePost(HttpExchange httpExchange) throws IOException {
        String urlString = httpExchange.getRequestURI().toString();
        String [] urlComps = urlString.split("/");
        String operation = urlComps[urlComps.length-1];

        switch (operation){
            case "transfer":
                handleTransfer(httpExchange);
                break;
            case "create":
                handleCreate(httpExchange);
                break;
            default:
                httpExchange.sendResponseHeaders(400,0);
        }
    }

    private void handleCreate(HttpExchange httpExchange) throws IOException {
        Account account = readRequest(httpExchange.getRequestBody(), Account.class);
        if(account == null){
            httpExchange.sendResponseHeaders(400,0);
            return;
        }

        RestResponse response = create(account);

        writeResult(httpExchange, response,200);
    }

    private void handleTransfer(HttpExchange httpExchange) throws IOException {
        TransferTransaction tx = readRequest(httpExchange.getRequestBody(),TransferTransaction.class);
        if(tx == null) {
            httpExchange.sendResponseHeaders(400,0); // bad request
            return;
        }

        RestResponse response = transfer(tx);

        writeResult(httpExchange,response,200);
    }

}
