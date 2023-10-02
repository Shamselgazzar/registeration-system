package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void listUserAccounts() {
        System.out.println("List of user accounts for " + this.getName() + ":");
        this.accounts.forEach(System.out::println);
    }

    public void deposit(long accountId, double amount){
        Account accountToDeposit = this.accounts.stream()
                .filter(account -> account.getAccountId() == accountId)
                .findFirst().orElse(null);

        if (accountToDeposit != null) {
            accountToDeposit.deposit(amount);
            System.out.println("Deposit successful. New balance: " + accountToDeposit.getBalance());
        } else {
            System.out.println("Account with ID " + accountId + " not found.");
        }

    }

    public void withdraw(long accountId, double amount){
        Account accountToWithdraw = this.accounts.stream()
                .filter(account -> account.getAccountId() == accountId)
                .findFirst().orElse(null);

        if (accountToWithdraw != null) {
            accountToWithdraw.withdraw(amount);
            } else {
            System.out.println("Account with ID " + accountId + " not found.");
        }

    }

}


