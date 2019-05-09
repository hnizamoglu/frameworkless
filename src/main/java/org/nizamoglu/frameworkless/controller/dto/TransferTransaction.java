package org.nizamoglu.frameworkless.controller.dto;

public class TransferTransaction {
    private Integer sender;
    private Integer receiver;
    private long amount;

    public TransferTransaction() {
    }

    public TransferTransaction(Integer sender, Integer receiver, long amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
