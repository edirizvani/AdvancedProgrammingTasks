package aud3.aud3.bank;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
    ArrayList<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public double totalAssets() {
       return accounts.stream().mapToDouble(account->account.getBalance()).sum();
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public void addInterest() {

        for (Account acc : accounts) {
            if (acc.getType() == AccountType.INTEREST) {
                ((InterestBearingAccount) acc).addInterest();
            }
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numAccounts = Integer.parseInt(scanner.nextLine());

        Random random = new Random();
        Bank bank = new Bank();

        for (int i = 0; i < numAccounts; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\s++");

            int type = random.nextInt(3);
            if (type == 0)
                bank.addAccount(new InterestCheckingAccount(parts[0], Double.parseDouble(parts[1])));
            else if (type == 1)
                bank.addAccount(new PlatiniumCheckingAccount(parts[0], Double.parseDouble(parts[1])));
            else
                bank.addAccount(new NonInterestCheckingAccount(parts[0], Double.parseDouble(parts[1])));
        }

        System.out.println("--------- TEST BANK AND TOTAL ASSETS ---------");
        System.out.println(bank);
        System.out.println(bank.totalAssets());

        System.out.println("--------- TEST ADD INTEREST ---------");
        bank.addInterest();
        System.out.println(bank.totalAssets());
    }
}


