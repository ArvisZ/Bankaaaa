
public class Customer {
    private final String firstName;
    private final String lastName;
    private final String ssn;
    private final  Account account;
    
    Customer(String firstName, String lastName, String ssn, Account account)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.account = account;
    }
    
    @Override
    public String toString()
    {
        return "\nCustomer information\n"+
                "First name " + firstName + "\n" +
                "Last name " + lastName + "\n" +
                "SNN " + ssn + "\n" +
                account;
    }
    
    public String basicInfo()
    {
        return " Account number " +account.getAccountNumber() + " - Name: " + firstName + " " + lastName;
    }
    
    Account getAccount()
    {
        return account;
    }
}
