import java.util.Random;

public class Account {
    // Instance variables to hold the state of an account
    private double balance; // The current balance of the account
    private String owner; // The name of the account owner
    private long accountNumber; // A unique number identifying the account

    // Static variables to keep track of class-wide information
    private static int numAccounts = 0; // The total number of accounts created
    private static int numDepositsToday = 0; // The total number of deposits made today across all accounts
    private static double totalDepositedToday = 0; // The total amount of money deposited today across all accounts
    private static int numWithdrawalsToday = 0; // The total number of withdrawals made today across all accounts
    private static double totalWithdrawnToday = 0; // The total amount of money withdrawn today across all accounts

    // Constructor for initializing an account with a specific balance, owner, and account number
    public Account(double initBal, String owner, long number) {
        this.balance = initBal; // Set the initial balance
        this.owner = owner; // Set the owner's name
        this.accountNumber = number; // Set the account number
        numAccounts++; // Increment the total number of accounts
    }

    // Constructor for initializing an account with a specific balance and owner, generates a random account number
    public Account(double initBal, String owner) {
        this(initBal, owner, new Random().nextLong());
    }

    // Constructor for initializing an account with an owner and zero balance
    public Account(String owner) {
        this(0, owner);
    }

    // Method to deposit a certain amount into the account
    public void deposit(double amount) {
        balance += amount; // Add the deposit amount to the balance
        numDepositsToday++; // Increment the count of deposits made today
        totalDepositedToday += amount; // Add the amount to the total deposited today
    }

    // Method to withdraw a certain amount from the account
    public void withdraw(double amount) {
        balance -= amount; // Subtract the withdrawal amount from the balance
        numWithdrawalsToday++; // Increment the count of withdrawals made today
        totalWithdrawnToday += amount; // Add the amount to the total withdrawn today
    }

    // Overloaded withdraw method that includes a transaction fee
    public void withdraw(double amount, double fee) {
        withdraw(amount + fee); // Withdraw the amount plus the fee
    }

    // Getter method for the account balance
    public double getBalance() {
        return balance;
    }

    // Getter method for the account number
    public long getAccountNumber() {
        return accountNumber;
    }

    // Overridden toString method to provide a string representation of the account
    public String toString() {
        return "Account Number: " + accountNumber + " | Owner: " + owner + " | Balance: " + balance;
    }

    // Static method to get the total number of accounts
    public static int getNumAccounts() {
        return numAccounts;
    }

    // Method to close an account, marks it as CLOSED and resets the balance
    public void close() {
        owner += " - CLOSED"; // Mark the account owner as CLOSED
        balance = 0; // Reset the balance to zero
        numAccounts--; // Decrement the total number of accounts
    }

    // Static method to consolidate two accounts into one
    public static Account consolidate(Account acct1, Account acct2) {
        if (!acct1.owner.equals(acct2.owner) || acct1.accountNumber == acct2.accountNumber) {
            System.out.println("Accounts cannot be consolidated."); // Print an error if accounts can't be consolidated
            return null;
        } else {
            Account newAccount = new Account(acct1.balance + acct2.balance, acct1.owner); // Create a new account with combined balance and same owner
            acct1.close(); // Close the first account
            acct2.close(); // Close the second account
            return newAccount; // Return the new consolidated account
        }
    }

    // Instance method to transfer an amount from this account to another
    public void transfer(Account acct, double amount) {
        if (this.balance >= amount) { // Check if the balance is sufficient for the transfer
            this.withdraw(amount); // Withdraw the amount from this account
            acct.deposit(amount); // Deposit the amount into the target account
        }
    }

    // Static method to transfer an amount from one account to another
    public static void transfer(Account acct1, Account acct2, double amount) {
        acct1.transfer(acct2, amount); // Use the instance method to perform the transfer
    }

    // Static method to get the daily statistics across all accounts
    public static String getDailyStats() {
        return "Total Deposits Today: " + numDepositsToday + 
               " | Total Deposited: " + totalDepositedToday + 
            //    " | Total Withdrawals Today: " + numWithdrawalsToday + 
               " | Total Withdrawn: " + totalWithdrawnToday;
    }
}
