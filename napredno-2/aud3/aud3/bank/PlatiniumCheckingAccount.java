package aud3.aud3.bank;

import aud3.aud3.bank.InterestCheckingAccount;

public class PlatiniumCheckingAccount extends InterestCheckingAccount {

    public PlatiniumCheckingAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public void addInterest() {
        super.deposit(super.getBalance()*interest*2);
    }

    @Override
    public String toString() {
        return super.toString()+" Type"+super.getType();
    }
}

