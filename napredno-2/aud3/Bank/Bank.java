package aud3.Bank;

import java.util.ArrayList;

public class Bank {

    ArrayList<Account> acounts;
    private int totalAccounts;
    private int maxAccounts;

    public Bank(int maxAccounts){
        this.maxAccounts=maxAccounts;
        this.acounts=new ArrayList<>();
        this.totalAccounts=0;
    }
    public void addAccount(Account account){
        acounts.add(account);
    }

    public double totalAssets(){
        double total=0.0;
        for(Account acount: acounts){
            total+=acount.getCurrentAmount();
        }
        return total;
    }

    public void addInterestToAllAccounts(){
       for (Account acc: acounts){
//            if(acc instanceof InterestCheckingAccount){
//                InterfaceBearingAccount interfaceBearingAccount=(InterfaceBearingAccount) acc;
//                interfaceBearingAccount.addInterest();
//            }
           if(acc.getAccountType().equals(AccountType.INTEREST)){
               InterfaceBearingAccount interfaceBearingAccount=(InterfaceBearingAccount) acc;
                  interfaceBearingAccount.addInterest();
           }
           }
    }

}
