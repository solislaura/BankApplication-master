import account.Account;
import account.CheckingAccount;
import account.SavingsAccount;
import bank.Bank;
import customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class LauraTest {
    Bank testBank;
    Account savings;
    Account checking;
    String openingDate;
    CheckingAccount checking2;

    @BeforeEach
    public void setup() {
        testBank = new Bank("Test Bank");
        savings = new SavingsAccount(testBank.generateAccountNumber(), 10000.00);
        savings.setAccountNumber("123-456-789");
        savings.setInterestRate(0.07);
        savings.setMaxTransactionNumber(10);
        savings.setBalance(10000);
        savings.setMinimumBalance(500);
        savings.setOpeningDate("11-19-2021");
        checking =  new CheckingAccount(testBank.generateAccountNumber(), 5000.00);
        checking.setBalance(5000);
        checking.setMinimumBalance(250);
        checking.setMaxTransactionNumber(20);
        checking.setInterestRate(0.001);
        checking.setAccountNumber("789-456-123");
        checking2 = new CheckingAccount(testBank.generateAccountNumber(), 750);
        checking2.setAccountNumber("111-222-333");

    }

    @Test
    public void getInterestRateTest() {
        double interestExpected = 0.07;
        Assertions.assertEquals(interestExpected, savings.getInterestRate());
    }

    @Test
    public void testMinimumBalance() {
        double minBalance = 250;
        Assertions.assertEquals(minBalance, checking.getMinimumBalance());
    }

    @Test
    public void testMaxTransactionNumber() {
        int expectedNumber = 10;
        Assertions.assertEquals(expectedNumber, savings.getMaxTransactionNumber());
    }

    //Debit with sufficient funds
    @Test
    public void debitAmountTest1() {
        checking.debitAmount(1000);
        double expected = 6000;
        Assertions.assertEquals(expected, checking.getBalance());
    }

    //Debit with insufficient funds
    @Test
    public void debitAmountTest2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> checking.debitAmount(7000));
        String expected = "Insufficient funds";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    //Testing with sufficient funds
    @Test
    public void testTransferAmount1() {
        savings.transferAmount(checking, 500);
        double expectedCheckingBalance = 5500;
        double expectedSavingsBalance = 9500;
        Assertions.assertEquals(expectedCheckingBalance, checking.getBalance());
        Assertions.assertEquals(expectedSavingsBalance, savings.getBalance());

    }

    //Testing with sufficient funds
    @Test
    public void testTransferAmount2() {
        checking.transferAmount(savings, 500);
        double expectedCheckingBalance = 4500;
        double expectedSavingsBalance = 10500;
        Assertions.assertEquals(expectedCheckingBalance, checking.getBalance());
        Assertions.assertEquals(expectedSavingsBalance, savings.getBalance());

    }

    //Test without sufficient funds
    @Test
    public void testTransferAmount3() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> checking.transferAmount(savings, 2000500));
        String expected = "Insufficient funds";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    //Test without sufficient funds
    @Test
    public void testTransferAmount4() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> savings.transferAmount(checking, 2000500));
        String expected = "Insufficient funds";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    public void testOpeningDate() {
        String expected = "11-19-2021";
        Assertions.assertEquals(expected, savings.getOpeningDate());
    }

    @Test
    public void testCalculateInterestEarned1() {
        double expected = 10000*0.07*0.0833;
        Assertions.assertEquals(expected, savings.calculateInterestEarned());
    }
    @Test
    public void testCalculateInterestEarned2() {
        double expected = 5000*0.001*0.0833;
        Assertions.assertEquals(expected, checking.calculateInterestEarned());
    }

    @Test
    public void testAddInterest() {
        double expected = 5000+(5000*0.001*0.0833);
        double actual = checking.calculateInterestEarned() + checking.addInterest();
        Assertions.assertEquals(expected,actual);
    }


}
