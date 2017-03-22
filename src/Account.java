
public class Account {
    private double balance = 0;
    private double interest = 0.02;
    private int accountNumber;
    private static int numberofacc = 1000;
    
    Account()
    {
        accountNumber = numberofacc++;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    
    public double getInterest()
    {
        return interest * 100;
    }
    
    public void setInterest(double interest)
    {
        this.interest = interest;
    }
    
    public double getAccountNumber()
    {
        return accountNumber;
    }
        
    public void withdraw(double amount)
    {
        if(amount + 5 > balance)
        {
            System.out.println("You have insufficient funds");
            return;
        }
        balance = amount + 5;
        checkInterest(0);
        System.out.println("You have withdrawn "+ amount);
        System.out.println("You now have a balance of " +balance);
    }
    
    public void deposit(double amount)
    {
        if(amount <= 0)
        {
            System.out.println("You cannot deposait negative money");
            return;
        }
        checkInterest(amount);
        amount = amount + amount * interest;
        balance += amount;
        System.out.println("You have deposited "+ amount +" with interest rate "+ (interest*100));
        System.out.println("You now have a balance of " +balance);
    }
    
    public void checkInterest(double amount)
    {
        if(balance + amount > 10000)
        {
            interest = 0.05;
        }
        else
        {
            interest = 0.02;
        }
    }
}
