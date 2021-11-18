package account;
import java.util.*;
import transaction.*;
import customer.*;


public class SavingsAccount extends Account{
    private static final double minimumBalance = 500.00;
    private static final double interestRate = 0.07;
    private double interestEarned;
    private double averageDailyBalance;
    private Date openingDate;


    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    @Override
    public double calculateInterestEarned() {
        interestEarned = averageDailyBalance * interestRate * time;
        return interestEarned;
    }
}
