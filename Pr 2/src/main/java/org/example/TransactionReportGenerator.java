
package org.example;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {
    public TransactionReportGenerator() {
    }

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        Iterator var1 = topExpenses.iterator();

        while (var1.hasNext()) {
            Transaction expense = (Transaction) var1.next();
            PrintStream var10000 = System.out;
            String var10001 = expense.getDescription();
            var10000.println(var10001 + ": " + expense.getAmount());
        }
    }

    public static void printExpensesSummary(List<Transaction> transactions) {
        Map<String, Map<String, Double>> expenseMap = new HashMap();
        double symbolValue = 1000.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Iterator var5 = transactions.iterator();

        while(var5.hasNext()) {
            Transaction transaction = (Transaction)var5.next();
            if (transaction.getAmount() < 0.0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), formatter);
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                expenseMap.putIfAbsent(monthYear, new HashMap());
                Map<String, Double> categoryMap = (Map)expenseMap.get(monthYear);
                String category = transaction.getDescription();
                categoryMap.put(category, (Double)categoryMap.getOrDefault(category, 0.0) + Math.abs(transaction.getAmount()));
            }
        }

        var5 = expenseMap.keySet().iterator();

        while(var5.hasNext()) {
            String monthYear = (String)var5.next();
            System.out.println("Місяць: " + monthYear);
            Map<String, Double> categoryMap = (Map)expenseMap.get(monthYear);
            Iterator var16 = categoryMap.keySet().iterator();

            while(var16.hasNext()) {
                String category = (String)var16.next();
                double totalExpense = (Double)categoryMap.get(category);
                int symbolCount = (int)(totalExpense / symbolValue);
                System.out.println(category + ": " + "*".repeat(symbolCount) + " (" + totalExpense + " грн)");
            }

            System.out.println();
        }

    }
}
