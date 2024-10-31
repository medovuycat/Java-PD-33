package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private String date;
    private double amount;
    private String description;

    public String toString() {
        return "Transaction{" +
                "date='" + this.date +
                "', amount=" + this.amount +
                ", description='" +
                this.description + "'}";
    }

}
