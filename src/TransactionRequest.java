import java.time.LocalDate;

/**
 * Represents a transaction request with essential transaction details.
 * Important:
 * - Only the execution date can be modified externally if needed.
 * - Fields such as transactionId, userId, and amount are immutable to ensure data integrity.
 * - Transaction fees can be calculated and updated as needed.
 */
public class TransactionRequest {
    private final long transactionId;
    private final long userId;
    private final LocalDate executionDate;
    private final double amount;
    private double transactionFee;

    /**
     * Constructor to initialize the transaction request.
     *
     * @param id             Unique transaction ID.
     * @param userId         The ID of the user performing the transaction.
     * @param executionDate  The date the transaction is scheduled to execute.
     * @param amount         The amount of money involved in the transaction.
     */
    public TransactionRequest(long id, long userId, LocalDate executionDate, double amount) {
        this.transactionId = id;
        this.userId = userId;
        this.executionDate = executionDate;
        this.amount = amount;
    }

    // Getters to retrieve transaction details. These values are immutable and cannot be changed after creation.

    public long getTransactionId() {
        return this.transactionId;  // Returns the unique transaction ID.
    }

    public long getUserId() {
        return userId;  // Returns the user ID associated with the transaction.
    }

    public LocalDate getExecutionDate() {
        return this.executionDate;  // Returns the scheduled execution date.
    }

    public double getAmount() {
        return this.amount;  // Returns the transaction amount.
    }

    // Getter and setter for transaction fee.

    public double getTransactionFee() {
        return this.transactionFee;  // Returns the calculated transaction fee.
    }

    /**
     * Sets the transaction fee after calculation.
     *
     * @param transactionFee The fee associated with the transaction.
     */
    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

}
