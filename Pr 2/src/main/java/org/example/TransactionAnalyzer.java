package org.example;

import org.example.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    public TransactionAnalyzer() {
    }

    public static int countTransactionsByMonth(String monthYear, List<Transaction> transactions) {
        int count = 0;
        Iterator var3 = transactions.iterator();

        while(var3.hasNext()) {
            Transaction transaction = (Transaction)var3.next();
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                ++count;
            }
        }

        return count;
    }

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0.0;

        Transaction transaction;
        for(Iterator var3 = transactions.iterator(); var3.hasNext(); balance += transaction.getAmount()) {
            transaction = (Transaction)var3.next();
        }

        return balance;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return (List)transactions.stream().filter((t) -> {
            return t.getAmount() < 0.0;
        }).sorted(Comparator.comparing(Transaction::getAmount)).limit(10L).collect(Collectors.toList());
    }
}
