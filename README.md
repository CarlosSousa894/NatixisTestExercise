Transaction API

A simple Java app to manage transactions. 
You can add, get, edit, and remove transactions as per the exercise requirements.

Features
Add transactions.
Get transactions.
Edit transactions.
Remove transactions.

Files
TransactionAPI.java: Manages transactions.
Transaction.java: Transaction details.
TransactionRequest.java: Handles transaction requests.
Main.java: Test the app here.

How to Run:
java Main.java

Testing
Add tests in Main.java like this:

public class Main {
public static void main(String[] args) {
TransactionAPI api = new TransactionAPI();

        // Add transaction
        Transaction t = new Transaction(6L, 106L, LocalDate.now(), 950.00);
        api.addTransaction(t);
        System.out.println("Added: " + t.getTransactionFee());

        // Edit transaction date
        api.editTransaction(6L, LocalDate.now().plusDays(5), 950.00);
        System.out.println("Date updated.");

        // Remove transaction
        api.removeTransaction(t);
        System.out.println("Removed.");
    }
}

Check Output
Use System.out.println() to see results.
Errors like "Invalid Date" or "Transaction doesn't exist" will appear if something is wrong.

I set a variable PassedTestCount that's added every time a Test passes, if in the end of execution it has 
the same value as the number of tests, then every Test passed!

Hope this solution met the requirements you were looking for.

Thank you