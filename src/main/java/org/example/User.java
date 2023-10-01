package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    private final long userId;
    private final String name;
    private final String email;
    private final String password;
    private List<Account> accounts = new ArrayList<>();
    private static long idCounter = 1;

    public User() {
        this.userId = createNewId();
        this.name = "name";
        this.email = "email";
        this.password = "password";
    }

    public User(String name, String email, String password) {
        this.userId = createNewId();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(String name, String email, String password, List<Account> accounts) {
        this.accounts = accounts;
        this.userId = createNewId();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long sum (long x, long y){
        return x+y;
    }

    private long createNewId() {
       return idCounter++;
    }

    public void addAccount(AccountType accountType){
        this.accounts.add(new Account(this.userId, accountType, 0));
    }

    private static void listUserAccounts(User user) {
        System.out.println("List of user accounts for " + user.getName() + ":");
        for (Account account : accounts) {
            if (account.getAccountOwnerID() == user.getUserId()) {
                System.out.println(account);
            }
        }
    }

}


