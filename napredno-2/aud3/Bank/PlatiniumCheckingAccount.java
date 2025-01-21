package aud3.Bank;

public class PlatiniumCheckingAccount extends InterestCheckingAccount {


    public PlatiniumCheckingAccount(String accountOwner, double currentAmount) {
        super(accountOwner, currentAmount);
    }

    @Override
 public void addInterest() {
        addAmount(getCurrentAmount()*interest_rate*2);
    }
}
