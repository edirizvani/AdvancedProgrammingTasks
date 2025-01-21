package aud3.aud3.bank;

import aud3.aud3.bank.Account;
import aud3.aud3.bank.AccountType;

public class NonInterestCheckingAccount extends Account {
    public NonInterestCheckingAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public AccountType getType() {
        return AccountType.INTEREST;
    }
}
