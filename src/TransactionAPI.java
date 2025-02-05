import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
public class TransactionAPI {
    private Map<Long, Transaction> transactionMap = new HashMap<>();
    private Map<String, Long> tokenUserMap = new HashMap<>();

    public TransactionAPI() {
        mockDatabaseInit();
        mockTokenInit();
    }


    public Transaction getTransaction(long id) {
        return transactionMap.get(id);
    }

    public void addTransaction(Transaction transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);
    }

    private void mockDatabaseInit() {
        // Adding mock transactions
        transactionMap.put(1L, new Transaction(1L, 101L, LocalDate.of(2024, 2, 1), 250.00, 2.50));
        transactionMap.put(2L, new Transaction(2L, 102L, LocalDate.of(2024, 2, 2), 100.00, 1.00));
        transactionMap.put(3L, new Transaction(3L, 103L, LocalDate.of(2024, 2, 3), 500.00, 5.00));
        transactionMap.put(4L, new Transaction(4L, 104L, LocalDate.of(2024, 2, 4), 75.50, 0.75));
        transactionMap.put(5L, new Transaction(5L, 105L, LocalDate.of(2024, 2, 5), 300.00, 3.00));
    }

    private void mockTokenInit() {
        // Simulated token-user mapping
        tokenUserMap.put("token-101", 101L);
        tokenUserMap.put("token-102", 102L);
        tokenUserMap.put("token-103", 103L);
        tokenUserMap.put("token-104", 104L);
        tokenUserMap.put("token-105", 105L);
    }
}
