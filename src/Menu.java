
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Menu {
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;
    
    public static void main(String [] args)
    {
        Menu menu = new Menu();
        menu.runMenu();
    }
    
    public void runMenu()
    {
        printHeader();
        while(!exit)
        {
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }
    
    private void printHeader()
    {
        System.out.println("+--------------------------+");
        System.out.println("|        Wozaaa            |");
        System.out.println("|        Bank App          |");
        System.out.println("+--------------------------+");
    }
    
    private void printMenu()
    {
        displayHeader("Please make selections");
        System.out.println("1->Creat acc");
        System.out.println("2->Make deposit");
        System.out.println("3->Make winthdraw");
        System.out.println("4->List balance");
        System.out.println("0->Exit");
    }
    
    private int getInput()
    {
        int choice = -1;
        do{
            System.out.println("Enter your choice");
            try
            {
                choice = Integer.parseInt(keyboard.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid selection, numbers only");
            }
            if(choice < 0 || choice > 4)
            {
                System.out.println("Choise outside of range");
            }
        }while(choice < 0 || choice > 4);
        return choice;
    }
    
    private void performAction(int choice)
    {
        switch(choice)
        {
            case 0:
                System.out.println("Thank for using app");
                System.exit(0);
                break;
            case 1:
        {
            try {
                creatAccount();
            } catch (InvalidAccountTypeExeption ex) {
                System.out.println("Account was not created successfully");
            }
        }
                break;
            case 2:
                makeDeposit();
                break;
            case 3:
                makeWinthdrawn();
                break;
            case 4:
                listBalances();
                break;
            default:
                System.out.println("Unknown error");
        }
    }
    
    private String askQuestion(String question, List<String> answers)
    {
        String response = "";
        Scanner input = new Scanner(System.in);
        boolean choices = ((answers == null) || answers.size() == 0) ? false : true;
        boolean firstRun = true;
        do
        {
            if(!firstRun)
            {
                System.out.println("Invalid selection. Pleases try again");
            }
            System.out.print(question);
            if(choices)
            {
                System.out.print("(");
                for(int i = 0; i < answers.size() - 1; ++i)
                {
                    System.out.print(answers.get(i) + "/");
                }
                System.out.print(answers.get(answers.size() -1));
                System.out.print("): ");
            }
            response = input.nextLine();
            firstRun = false;
            if(!choices)
            {
                break;
            }
        }
        while(!answers.contains(response));
        return response;
    }
    
    private double getDeposit(String accountType)
    {
        double initialDeposit = 0;
        Boolean valid = false;
        while(!valid)
        {
            System.out.print("Enter inital deposit ");
            try
            {
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("Deposit must be number");
            }
            if(accountType.equalsIgnoreCase("checking"))
            {
                if(initialDeposit < 100)
                {
                    System.out.println("Checking account require a minimum $100");
                }
                else
                {
                    valid = true;
                }
            } 
            else if(accountType.equalsIgnoreCase("savings"))
            {
                if(initialDeposit < 50)
                {
                    System.out.println("Savings account require a minimum $50");
                }
                else
                {
                    valid = true;
                }
            }
        }
        return initialDeposit;
    }
    
    private void creatAccount() throws InvalidAccountTypeExeption 
    {
        displayHeader("Creat an Account");
        String accountType = askQuestion("Please enter acc type: ", Arrays.asList("checking","savings"));
        String firstName = askQuestion("Plese enter first name", null);
        String lastName = askQuestion("Plese enter last name", null);
        String ssn = askQuestion("Plese enter social security number", null);
        double initialDeposit = getDeposit(accountType);
        Account account;
        if(accountType.equalsIgnoreCase("checking"))
        {
            account = new Checking(initialDeposit);
        }
        else if(accountType.equalsIgnoreCase("savings"))
        {
            account = new Savings(initialDeposit);
        }
        else
        {
            throw new InvalidAccountTypeExeption();
        }
        Customer customer = new Customer(firstName, lastName, ssn, account);
        bank.addCustomer(customer);
    }

    private double getDollarAmount(String question)
    {
        System.out.print(question);
        double amount = 0;
        try
        {
            amount = Double.parseDouble(keyboard.nextLine());
        }
        catch(NumberFormatException e)
        {
            amount = 0;
        }
        return amount;
    }
    
    private void makeDeposit() 
    {
        displayHeader("Make a Deposit ");
        int account = selectAccount();
        if(account >= 0)
        {
            double amount = getDollarAmount("How much would you likr to deposit?: ");
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    private void makeWinthdrawn() 
    {
       displayHeader("Make a Withdrawl");
       int account = selectAccount();
        if(account >= 0)
        {
            double amount = getDollarAmount("How much would you likr to withdraw?: ");
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
    }

    private void listBalances() 
    {
        displayHeader("List Account Details");
        int account = selectAccount();
        if(account >= 0)
        {
            displayHeader("Account Details");
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }
    
    private void displayHeader(String message)
    {
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for(int i = 0; i < width; ++i)
        {
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("|   " + message + "   |");
        System.out.println(sb.toString());
    }
    
    private int selectAccount()
    {
        ArrayList<Customer> customers = bank.getCustomers();
        if(customers.size() <= 0)
        {
            System.out.println("No customers at bank");
            return -1;
        }
        System.out.println("Select account ");
        for(int i = 0; i < customers.size(); i++)
        {
            System.out.println("\t" + (i+1)+") "+customers.get(i).basicInfo());
        }
        int account;
        System.out.print("Enter your selection ");
        try
        {
            account = Integer.parseInt(keyboard.nextLine()) -1;
        }
        catch(NumberFormatException e)
        {
            account = -1;
        }
        if(account < 0 || account > customers.size())
        {
            System.out.println("Invalid account selected");
            account = -1;
        }
        return account;
    }
}
