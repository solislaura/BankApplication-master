package transaction;

import account.*;


public class TransferTransaction extends Transaction{
    Account recieverAccount;
    Account fromAccount;

    public  TransferTransaction (Double amount, Account recieverAccount, Account fromAccount){
        this.transactionType="Transfer";
        this.amount=amount;
        this.transactionId=Transaction.ID;
        this.recieverAccount=recieverAccount;
        this.fromAccount=fromAccount;
        Transaction.ID++;
        
    }
    @Override
    public Double apply(Double balance) {
        // TODO Auto-generated method stub
        Double newBalance=balance-amount;
        this.balanceAfterTransaction=newBalance;
        recieverAccount.Recieve(fromAccount, amount);
        System.out.println(printTransaction());
        return newBalance;
    }
    
    public  String printTransaction(){

        return "Transaction type: "+transactionType+ " transactionId: "+transactionId+" amount: "+amount+" sent to "+recieverAccount.getAccountNumber()+" balance after transaction: "+balanceAfterTransaction
                
        ;
       
   };
}
