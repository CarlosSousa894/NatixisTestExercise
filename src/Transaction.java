
import java.time.LocalDate;

public class Transaction {
    private final long transactionId;
    private final long userId;
    private final LocalDate executionDate;
    private final double amount;
    private final double transactionFee;
    private boolean wasExecuted = false;

    public Transaction(long id, long userId, LocalDate executionDate, double amount, double transactionFee) {
        this.transactionId = id;
        this.userId = userId;
        this.executionDate = executionDate;
        this.amount = amount;
        this.transactionFee = transactionFee;
    }

    public long getTransactionId() {
        return this.transactionId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getExecutionDate() {
        return this.executionDate;
    }

    public double getAmount() {
        return this.amount;
    }

    public double getTransactionFee() {
        return this.transactionFee;
    }
}
