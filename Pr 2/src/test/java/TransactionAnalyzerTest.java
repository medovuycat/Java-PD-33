import java.util.Arrays;
import java.util.List;
import org.example.Transaction;
import org.example.TransactionAnalyzer;
import org.example.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionAnalyzerTest {
    TransactionAnalyzerTest() {
    }

    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);
        int countFeb = TransactionAnalyzer.countTransactionsByMonth("02-2023", transactions);
        int countMar = TransactionAnalyzer.countTransactionsByMonth("03-2023", transactions);
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testCSVReader() {
        List<Transaction> expectedTransactions = List.of(new Transaction("05-12-2023", -7850.0, "Сільпо"), new Transaction("07-12-2023", -1200.0, "Аптека"), new Transaction("10-12-2023", 80000.0, "Зарплата"));
        List<Transaction> actualTransactions = TransactionCSVReader.readTransactions("https://informer.com.ua/dut/java/pr2.csv");

        for (int i = 0; i < expectedTransactions.size(); ++i) {
            Assertions.assertEquals(expectedTransactions.get(i), actualTransactions.get(i), "Транзакції не збігаються на позиції: " + i);
        }

    }

    @Test
    public void testTopExpenses() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertNotNull(transactions, "Список транзакцій не повинен бути null");
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій не повинен бути порожнім");

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertNotNull(topExpenses, "Список найбільших витрат не повинен бути null");
        Assertions.assertFalse(topExpenses.isEmpty(), "Список найбільших витрат не повинен бути порожнім");
        Assertions.assertTrue(topExpenses.size() <= 10, "Список найбільших витрат повинен містити не більше 10 елементів");

        Transaction firstExpense = topExpenses.get(0);
        Assertions.assertNotNull(firstExpense.getDate(), "Дата транзакції не повинна бути null");
        Assertions.assertNotNull(firstExpense.getDescription(), "Опис транзакції не повинен бути null");

        System.out.println("Топ 10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense);
        }
    }
}