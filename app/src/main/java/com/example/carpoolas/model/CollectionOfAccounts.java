package com.example.carpoolas.model;

import androidx.annotation.NonNull;

import java.util.Iterator;
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


    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // using a string builder is more ef

        for (Account acc : this.accounts) { // java.util.list supports for-each loop
            sb.append(acc.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    }

