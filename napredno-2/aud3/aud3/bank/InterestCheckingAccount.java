package aud3.aud3.bank;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {

    public static final float interest=0.03F;

    public InterestCheckingAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public AccountType getType() {
        return AccountType.INTEREST;
    }

    @Override
    public void addInterest() {
        super.deposit(super.getBalance()*interest);
    }

    @Override
    public String toString() {
        return super.toString()+" Type"+getType();
    }
}
