
public class Checking extends Account{
    private static String accountType ="Checking";
    
    Checking(double initialDeposit)
    {
        this.setBalance(initialDeposit);
        this.checkInterest(0);
    }
    
    @Override
    public String toString()
    {
        return "Account type " + accountType + " Acount\n"+
                "Acount number" + this.getAccountNumber() + "\n"+
                "Balance" + this.getBalance() + "\n"+
                "Interest rate" + this.getInterest() + "\n";
    }
}
