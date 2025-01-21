package aud3.Bank;

public abstract class Account {

        private String accountOwner;
        private int id;
        private static int idSeed=221500;
        private AccountType type;
        double currentAmount;

    public Account(String accountOwner, double currentAmount) {
        this.accountOwner = accountOwner;
        this.id = idSeed;
        idSeed++;
        this.currentAmount = currentAmount;
    }
    public abstract AccountType getAccountType();

    public double getCurrentAmount(){
        return currentAmount;
    }

    public void addAmount(double amount){
        this.currentAmount+=amount;
    }
    public void withdrawAmount(double amount) throws CannotWithdrawMoneyException {
        if (currentAmount < amount)
            throw new CannotWithdrawMoneyException(currentAmount, amount);
            currentAmount -= amount;


    }


}
