package account;
import java.util.*;
import transaction.*;

import customer.*;

public class CheckingAccount extends Account {
    private static final double minimumBalance = 250.00;
    private static final double interestRate = 0.001;
    private double interestEarned;
    private double time = 0.0833;
    private double balance;
    private String debitCardNumber;

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        this.balance = balance;
        this.debitCardNumber = accountNumber;
        
    }


    @Override
    public double calculateInterestEarned() {
        interestEarned = balance * interestRate * time;
        return interestEarned;
    }

}
