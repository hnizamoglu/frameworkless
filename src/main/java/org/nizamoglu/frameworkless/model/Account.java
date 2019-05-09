package org.nizamoglu.frameworkless.model;

import java.util.Objects;

public class Account {
    private Integer id;
    private String name;
    private Long balance;

    public Account() {
    }

    public Account(Integer id, String name, Long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Account(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance);
    }

    @Override
    public Account clone() {
        return new Account(id,name,balance);
    }
}
