package aud3.Bank;

public class NICA extends Account{
    public NICA(String accountOwner, double currentAmount) {
        super(accountOwner, currentAmount);
    }
    public  AccountType getAccountType(){
        return AccountType.NON_INTEREST;
    }
}
