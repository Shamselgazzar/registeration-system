package org.example;

public class Account {
    private final long accountId;
    private final long ownerId;
    private final AccountType accountType;
    private long balance;
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

    public void withdraw(long amount){
        if (amount <= balance) {
            balance -= amount;
        }else{
            System.out.println("insufficient funds");
        }
    }

    public void deposit(long amount){
        balance += amount;
    }

}

