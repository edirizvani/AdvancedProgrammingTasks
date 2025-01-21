package aud3.Bank;

public  class InterestCheckingAccount extends Account implements InterfaceBearingAccount{

   public static final double interest_rate=0.03;


    public InterestCheckingAccount(String accountOwner, double currentAmount) {
        super(accountOwner, currentAmount);
    }
    @Override
    public  AccountType getAccountType(){
        return AccountType.INTEREST;
    }
    @Override
    public void addInterest() {
        addAmount(getCurrentAmount()*interest_rate);
    }
}
