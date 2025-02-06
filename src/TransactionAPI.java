import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class TransactionAPI {
    // In-memory data store to simulate a database
    private final Map<Long, TransactionRequest> transactionMap = new HashMap<>();

    // Constructor initializes the mock database with sample transactions
    public TransactionAPI() {
        mockDatabaseInit();
    }

    /**
     * Retrieves a transaction by its ID.
     * @param transactionId The unique ID of the transaction.
     * @return The Transaction object.
     * @throws IllegalArgumentException if the transaction is not found.
     */
    public TransactionRequest getTransaction(long transactionId) {
        TransactionRequest transactionRequest = transactionMap.get(transactionId);
        if (transactionRequest == null) {
            throw new IllegalArgumentException("Transaction not found.");
        }
        return transactionRequest;
    }

    /**
     * Adds a new transaction to the system.
     * Validates transaction details such as date and amount, and calculates the transaction fee.
     * @param transactionRequest The Transaction object to be added.
     * @return true if the transaction is successfully added.
     * @throws IllegalArgumentException if the transaction is invalid or already exists.
     */
    public boolean addTransaction(TransactionRequest transactionRequest) {
        // Check if the transaction already exists
        if(transactionMap.containsKey(transactionRequest.getTransactionId())) {
            throw new IllegalArgumentException("Transaction already exists.");
        }

        // Validate execution date and amount
        if(transactionRequest.getExecutionDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid Date.");
        }
        if(transactionRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid Amount.");
        }

        // Fee calculation for transactions less than 1000
        if(transactionRequest.getAmount() < 1000){
            if(transactionRequest.getExecutionDate().equals(LocalDate.now())){
                double tranFee = transactionRequest.getAmount() + (transactionRequest.getAmount() * 0.03) + 3;  // 3% fee + $3 fixed fee
                transactionRequest.setTransactionFee(tranFee);
                transactionMap.put(transactionRequest.getTransactionId(), transactionRequest);
            } else {
                throw new IllegalArgumentException("Invalid Date for this transaction");
            }
            return true;
        }

        // Calculate days between now and execution date
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), transactionRequest.getExecutionDate());

        // Fee calculation for transactions between 1001 and 2000
        if (transactionRequest.getAmount() >= 1001 && transactionRequest.getAmount() <= 2000) {
            if (daysBetween >= 1 && daysBetween <= 10) {
                double tranFee = transactionRequest.getAmount() + (transactionRequest.getAmount() * 0.09);  // 9% fee
                transactionRequest.setTransactionFee(tranFee);
                transactionMap.put(transactionRequest.getTransactionId(), transactionRequest);
            } else {
                throw new IllegalArgumentException("Execution date must be between 1 and 10 days from today");
            }
            return true;
        }

        // Fee calculation for transactions above 2000 with varying percentages based on execution date
        if (transactionRequest.getAmount() >= 2000) {
            if (daysBetween >= 11 && daysBetween <= 20) {
                double tranFee = transactionRequest.getAmount() + (transactionRequest.getAmount() * 0.082);  // 8.2% fee
                transactionRequest.setTransactionFee(tranFee);
                transactionMap.put(transactionRequest.getTransactionId(), transactionRequest);
            } else if (daysBetween >= 21 && daysBetween <= 30) {
                double tranFee = transactionRequest.getAmount() + (transactionRequest.getAmount() * 0.069);  // 6.9% fee
                transactionRequest.setTransactionFee(tranFee);
                transactionMap.put(transactionRequest.getTransactionId(), transactionRequest);
            } else if (daysBetween >= 31 && daysBetween <= 40) {
                double tranFee = transactionRequest.getAmount() + (transactionRequest.getAmount() * 0.047);  // 4.7% fee
                transactionRequest.setTransactionFee(tranFee);
                transactionMap.put(transactionRequest.getTransactionId(), transactionRequest);
            } else if (daysBetween >= 41) {
                double tranFee = transactionRequest.getAmount() + (transactionRequest.getAmount() * 0.017);  // 1.7% fee
                transactionRequest.setTransactionFee(tranFee);
                transactionMap.put(transactionRequest.getTransactionId(), transactionRequest);
            } else {
                throw new IllegalArgumentException("Execution date must be between 11 and 40+ days from today");
            }
        }
        return true;
    }

    /**
     * Removes an existing transaction from the system.
     * @param transactionRequest The transaction to be removed.
     * @return true if the transaction was successfully removed.
     * @throws IllegalArgumentException if the transaction does not exist.
     */
    public boolean removeTransaction(TransactionRequest transactionRequest) {
        if(!transactionMap.containsKey(transactionRequest.getTransactionId())) {
            throw new IllegalArgumentException("Transaction doesn't exist.");
        }
        transactionMap.remove(transactionRequest.getTransactionId());
        return true;
    }

    /**
     * Edits an existing transaction by updating its execution date and amount.
     * This method removes the old transaction and re-adds the updated one,
     * reusing the addTransaction logic for validation and fee calculation.
     * @param transactionId The ID of the transaction to be edited.
     * @param newExecutionDate The new execution date.
     * @param newAmount The new amount.
     * @return true if the transaction is successfully edited.
     * @throws IllegalArgumentException if the transaction doesn't exist or validation fails.
     */
    public boolean editTransaction(long transactionId, LocalDate newExecutionDate, double newAmount) {
        // Retrieve the existing transaction
        TransactionRequest existingTransactionRequest = getTransaction(transactionId);

        // Remove the existing transaction before updating
        removeTransaction(existingTransactionRequest);

        // Create a new transaction object with updated details
        TransactionRequest updatedTransactionRequest = new TransactionRequest(
                transactionId,
                existingTransactionRequest.getUserId(),  // Keep the same user ID
                newExecutionDate,
                newAmount
        );

        // Use addTransaction to validate and calculate fees
        addTransaction(updatedTransactionRequest);
        return true;
    }

    /**
     * Initializes the transactionMap with mock transactions for testing purposes.
     */
    private void mockDatabaseInit() {
        // Adding mock transactions with sample data
        transactionMap.put(1L, new TransactionRequest(1L, 101L, LocalDate.of(2024, 2, 1), 250.00));
        transactionMap.put(2L, new TransactionRequest(2L, 102L, LocalDate.of(2024, 2, 2), 100.00));
        transactionMap.put(3L, new TransactionRequest(3L, 103L, LocalDate.of(2024, 2, 3), 500.00));
        transactionMap.put(4L, new TransactionRequest(4L, 104L, LocalDate.of(2024, 2, 4), 75.50));
        transactionMap.put(5L, new TransactionRequest(5L, 105L, LocalDate.of(2024, 2, 5), 300.00));
    }
}
