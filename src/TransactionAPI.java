import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
public class TransactionAPI {
    private Map<Long, Transaction> transactionMap = new HashMap<>();
    public TransactionAPI() {
        mockDatabaseInit();
    }

    public Transaction getTransaction(long transactionId) {
        Transaction transaction = transactionMap.get(transactionId);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction not found.");
        }
        return transaction;
    }

    public void addTransaction(Transaction transaction) {
        if(transactionMap.containsKey(transaction.getTransactionId())) {
            throw new IllegalArgumentException("Transaction already exists.");
        }
        if(transaction.getExecutionDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid Date.");
        }
        if(transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid Amount.");
        }

        if(transaction.getAmount() < 1000){
            if(transaction.getExecutionDate().equals(LocalDate.now())){
                double tranFee =  transaction.getAmount() + (transaction.getAmount() * 0.03) + 3;
                transaction.setTransactionFee(tranFee);
                transactionMap.put(transaction.getTransactionId(), transaction);
            }else {
                throw new IllegalArgumentException("Invalid Date for this transaction");
            }
            return;
        }

        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), transaction.getExecutionDate());
        if (transaction.getAmount() >= 1001 && transaction.getAmount() <= 2000) {
            if (daysBetween >= 1 && daysBetween <= 10) {
                double tranFee = transaction.getAmount() + (transaction.getAmount() * 0.09);
                transaction.setTransactionFee(tranFee);
                transactionMap.put(transaction.getTransactionId(), transaction);
            } else {
                throw new IllegalArgumentException("Execution date must be between 1 and 10 days from today");
            }
            return;
        }

        if (transaction.getAmount() >= 2000) {
            if (daysBetween >= 11 && daysBetween <= 20) {
                double tranFee = transaction.getAmount() + (transaction.getAmount() * 0.082);
                transaction.setTransactionFee(tranFee);
                transactionMap.put(transaction.getTransactionId(), transaction);
            }else if (daysBetween >= 21 && daysBetween <= 30) {
                double tranFee = transaction.getAmount() + (transaction.getAmount() * 0.069);
                transaction.setTransactionFee(tranFee);
                transactionMap.put(transaction.getTransactionId(), transaction);
            }else if (daysBetween >= 31 && daysBetween <= 40) {
                double tranFee = transaction.getAmount() + (transaction.getAmount() * 0.047);
                transaction.setTransactionFee(tranFee);
                transactionMap.put(transaction.getTransactionId(), transaction);
            }else if (daysBetween >= 41) {
                double tranFee = transaction.getAmount() + (transaction.getAmount() * 0.017);
                transaction.setTransactionFee(tranFee);
                transactionMap.put(transaction.getTransactionId(), transaction);
            }else{
                throw new IllegalArgumentException("Execution date must be between 11 and 40+ days from today");
            }
        }
    }

    public void removeTransaction(Transaction transaction) {

    }

    private void mockDatabaseInit() {
        // Adding mock transactions
        transactionMap.put(1L, new Transaction(1L, 101L, LocalDate.of(2024, 2, 1), 250.00));
        transactionMap.put(2L, new Transaction(2L, 102L, LocalDate.of(2024, 2, 2), 100.00));
        transactionMap.put(3L, new Transaction(3L, 103L, LocalDate.of(2024, 2, 3), 500.00));
        transactionMap.put(4L, new Transaction(4L, 104L, LocalDate.of(2024, 2, 4), 75.50));
        transactionMap.put(5L, new Transaction(5L, 105L, LocalDate.of(2024, 2, 5), 300.00));
    }
}
