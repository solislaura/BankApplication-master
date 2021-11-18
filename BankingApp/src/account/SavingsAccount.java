package account;
import java.util.*;
import transaction.*;
import customer.*;


public class SavingsAccount extends Account{
    private static final double minimumBalance = 500.00;
    private static final double interestRate = 0.07;
    private double interestEarned;
    private double time = 0.0833;
    private double balance;


    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        this.balance = balance;
        
    }


    @Override
    public double calculateInterestEarned() {
        interestEarned = balance * interestRate * time;
        return interestEarned;
    }
}
