package aud3.aud3.bank;

public abstract class Account {
    static long ID=1L;
    String name;
    long accountID;
    double balance;

    AccountType type;

    public Account(String name, double balance) {
        this.name = name;
        this.accountID = ID++;
        this.balance = balance;
    }

    public static long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }
    public void deposit(double amount){
        balance-=amount;
    }
    public void withdrawn(double amount){
        balance+=amount;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f%",name,accountID,balance);
    }
}
