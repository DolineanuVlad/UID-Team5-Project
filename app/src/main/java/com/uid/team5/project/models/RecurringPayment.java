package com.uid.team5.project.models;

/**
 * Created by Gabriel on 1/7/2018.
 */

public class RecurringPayment {
    public RecurringPayment(String name, String date, String amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String name;
    private String date;
    private String amount;
}
