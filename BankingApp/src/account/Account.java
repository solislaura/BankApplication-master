package account;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import transaction.*;
import customer.*;
import bank.*;


public abstract class Account {
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactions=new ArrayList<>();
    private double interestRate;
    private  double interestEarned;
    private  double minimumBalance;
    private  int maxTransactionNumber;
    public static final double time = 0.0833;
    private  int countTransactions;
    private String openingDate;

    
    private ArrayList<Customer> customers;


    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;

       customers = new ArrayList<>();
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public int getMaxTransactionNumber() {
        return maxTransactionNumber;
    }

    public void setMaxTransactionNumber(int maxTransactionNumber) {
        this.maxTransactionNumber = maxTransactionNumber;
    }

    public void debitAmount(double debit) {
        if (debit > 0) {
            if (balance >= debit) {
                Transaction transaction =new DepositTransaction(debit);
                this.balance=transaction.apply(balance);
                transactions.add(transaction);
            } else {
                throw new IllegalArgumentException("Insufficient funds.");
            }
        }
    }


    public void transferAmount(Account toAccount, double transferAmount) {
        if (this.getBalance() > transferAmount && transferAmount > 0) {
           Transaction transaction=new TransferTransaction(transferAmount, toAccount,this);
           this.balance=transaction.apply(balance);
           transactions.add(transaction);
            countTransactions++;
        } else {
            throw new IllegalArgumentException("Insufficient funds.");
        }
    }

    public void Recieve(Account fromAccount,double debit){
        
        Transaction transaction =new RecieveTransaction(debit, fromAccount);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
    }
    public void creditAmount(double credit) {
        if (credit > 0) {
            //this.balance += credit;
            Transaction transaction =new WithdrawTransaction(credit);
            this.balance=transaction.apply(balance);
            transactions.add(transaction);
            countTransactions++;
        } else {
            System.out.println("Credit amount must be greater than zero.");
        }
    }

    
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double calculateInterestEarned() {
        interestEarned = balance * interestRate * time;
        return interestEarned;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public double addInterest() {
        balance += interestEarned;
        return balance;
    }

    public String printBankStatement(){
        String statement="";
        for(Transaction t:transactions){
            statement+=t.printTransaction()+"\n";
        }
        return statement;        
    }


}
