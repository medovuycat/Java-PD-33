package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {
    public TransactionCSVReader() {
    }

    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList();

        try {
            URL url = new URL(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String line;
            try {
                while((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
                    transactions.add(transaction);
                }
            } catch (Throwable var8) {
                try {
                    br.close();
                } catch (Throwable var7) {
                    var8.addSuppressed(var7);
                }

                throw var8;
            }

            br.close();
        } catch (IOException var9) {
            IOException e = var9;
            e.printStackTrace();
        }

        return transactions;
    }
}
