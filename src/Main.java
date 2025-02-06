import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create a new instance of TransactionAPI
        TransactionAPI transactionAPI = new TransactionAPI();
        int PassedTestCount = 0;
        // Test 1: Retrieve an existing transaction
        System.out.println("Test 1: Retrieve existing transaction (ID 1)");
        try {
            TransactionRequest retrievedTransaction = transactionAPI.getTransaction(1L);
            assert retrievedTransaction.getTransactionId() == 1L : "Transaction ID mismatch";
            System.out.println("Passed: Retrieved Transaction: " + retrievedTransaction);
            PassedTestCount++;
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        // Test 2: Add a new valid transaction
        System.out.println("\nTest 2: Add new valid transaction (ID 6)");
        try {
            TransactionRequest newTransaction = new TransactionRequest(6L, 106L, LocalDate.now(), 800.00);
            boolean added = transactionAPI.addTransaction(newTransaction);
            assert added : "Transaction was not added";
            assert transactionAPI.getTransaction(6L).getAmount() == 800.00 : "Amount mismatch after adding";
            System.out.println("Passed: Transaction added successfully: " + transactionAPI.getTransaction(6L));
            PassedTestCount++;
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        // Test 3: Attempt to add a duplicate transaction
        System.out.println("\nTest 3: Attempt to add duplicate transaction (ID 6)");
        try {
            TransactionRequest duplicateTransaction = new TransactionRequest(6L, 106L, LocalDate.now(), 800.00);
            transactionAPI.addTransaction(duplicateTransaction);
            System.out.println("Failed: Duplicate transaction added.");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Transaction already exists.") : "Unexpected error message";
            System.out.println("Passed: " + e.getMessage());
            PassedTestCount++;
        }

        // Test 4: Edit an existing transaction
        System.out.println("\nTest 4: Edit existing transaction (ID 6)");
        try {
            boolean edited = transactionAPI.editTransaction(6L, LocalDate.now().plusDays(5), 1500.00);
            assert edited : "Transaction edit failed";
            TransactionRequest editedTransaction = transactionAPI.getTransaction(6L);
            assert editedTransaction.getAmount() == 1500.00 : "Amount mismatch after editing";
            assert editedTransaction.getExecutionDate().equals(LocalDate.now().plusDays(5)) : "Date mismatch after editing";
            System.out.println("Passed: Edited Transaction: " + editedTransaction);
            PassedTestCount++;
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        // Test 5: Remove an existing transaction
        System.out.println("\nTest 5: Remove existing transaction (ID 6)");
        try {
            boolean removed = transactionAPI.removeTransaction(transactionAPI.getTransaction(6L));
            assert removed : "Transaction removal failed";
            System.out.println("Passed: Transaction removed successfully.");
            PassedTestCount++;
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        // Test 6: Attempt to retrieve a removed transaction
        System.out.println("\nTest 6: Attempt to retrieve removed transaction (ID 6)");
        try {
            transactionAPI.getTransaction(6L);
            System.out.println("Failed: Retrieved a transaction that should have been removed.");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Transaction not found.") : "Unexpected error message";
            System.out.println("Passed: " + e.getMessage());
            PassedTestCount++;
        }

        // Test 7: Add a transaction with an invalid date (in the past)
        System.out.println("\nTest 7: Add transaction with invalid date");
        try {
            TransactionRequest invalidDateTransaction = new TransactionRequest(7L, 107L, LocalDate.now().minusDays(1), 500.00);
            transactionAPI.addTransaction(invalidDateTransaction);
            System.out.println("Failed: Transaction with invalid date was added.");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Invalid Date.") : "Unexpected error message";
            System.out.println("Passed: " + e.getMessage());
            PassedTestCount++;
        }

        // Test 8: Add a transaction with invalid amount (zero)
        System.out.println("\nTest 8: Add transaction with invalid amount");
        try {
            TransactionRequest invalidAmountTransaction = new TransactionRequest(8L, 108L, LocalDate.now(), 0.00);
            transactionAPI.addTransaction(invalidAmountTransaction);
            System.out.println("Failed: Transaction with invalid amount was added.");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Invalid Amount.") : "Unexpected error message";
            System.out.println("Passed: " + e.getMessage());
            PassedTestCount++;
        }
        if(PassedTestCount == 8) {
            System.out.println("\nAll tests passed.");
        }else{
            System.out.println("\nSome tests failed.");
        }

    }
}
