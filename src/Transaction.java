
import java.time.LocalDate;

public class Transaction {
    private final long transactionId;
    private final long userId;
    private final LocalDate executionDate;
    private final double amount;
    private double transactionFee;
    private boolean wasExecuted = false;

    public Transaction(long id, long userId, LocalDate executionDate, double amount) {
        this.transactionId = id;
        this.userId = userId;
        this.executionDate = executionDate;
        this.amount = amount;

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

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }
}
