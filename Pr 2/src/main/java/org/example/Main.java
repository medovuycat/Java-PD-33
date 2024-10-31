package org.example;

import java.util.Iterator;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);
        Iterator var3 = transactions.iterator();

        while (var3.hasNext()) {
            Transaction transaction = (Transaction) var3.next();
            System.out.println(transaction);
        }

        System.out.println("__________");
        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);
        System.out.println("__________");
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);
        System.out.println("__________");
        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(monthYear, transactions);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);
        System.out.println("Кількість транзакцій за " + monthYear + ": " + transactionsCount);
        System.out.println("__________");
        TransactionReportGenerator.printExpensesSummary(transactions);
    }
}