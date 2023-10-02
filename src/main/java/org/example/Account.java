package org.example;

import lombok.Getter;

@Getter
public class Account {
    private final long accountId;
    private final long ownerId;
    private final AccountType accountType;
    private double balance;
    private static long accountCounter = 1;

    public Account(long ownerId, AccountType accountType, long balance) {
        this.accountId = createNewId();
        this.ownerId = ownerId;
        this.accountType = accountType;
        this.balance = balance;
    }

    private long createNewId() {
        return accountCounter++;
    }

    public void withdraw(double amount){
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw successful. New balance: " + this.getBalance());

        }else{
            System.out.println("insufficient funds");
        }
    }

    public void deposit(double amount){
        balance += amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", ownerId=" + ownerId +
                ", accountType=" + accountType +
                ", balance=" + balance +
                '}';
    }
}

