package com.example.carpoolas.model;

import java.util.LinkedList;
import java.util.List;

public class CollectionOfAccounts {
    public List<Account> accounts;

    public CollectionOfAccounts(){
        this.accounts = new LinkedList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(String username, String password, String name, String email){
        Account acc = new Account(username, password, name, email);
        this.accounts.add(acc);
    }
}
