package com.uid.team5.project;

import com.uid.team5.project.models.Expense;
import com.uid.team5.project.models.Member;
import com.uid.team5.project.models.RecurringPayment;

import java.util.ArrayList;

/**
 * Created by Gabriel on 1/9/2018.
 */

public class AppDataSingleton {


    public ArrayList<RecurringPayment> getRecurringPayments() {
        return recurringPayments;
    }

    public void setRecurringPayments(ArrayList<RecurringPayment> recurringPayments) {
        this.recurringPayments = recurringPayments;
    }

    private ArrayList<RecurringPayment> recurringPayments;
    private boolean enabledAssistant;

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    private ArrayList<Member> members;

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<Expense> expenses;



    public ArrayList<Expense> getCurrentInserionOfExpenses() {
        return currentInserionOfExpenses;
    }

    public void setCurrentInserionOfExpenses(ArrayList<Expense> currentInserionOfExpenses) {
        this.currentInserionOfExpenses = currentInserionOfExpenses;
    }

    public ArrayList<Expense> currentInserionOfExpenses;


    public void clearExpenses() {
        this.currentInserionOfExpenses = new ArrayList<>();
    }


    //singleton
    private static AppDataSingleton instance = null;

    public static AppDataSingleton getInstance()
    {
        if(instance == null)
            instance = new AppDataSingleton();

        return instance;
    }

    public AppDataSingleton()
    {
        recurringPayments = new ArrayList<>();
        members = new ArrayList<>();
        expenses = new ArrayList<>();
        currentInserionOfExpenses = new ArrayList<>();
        enabledAssistant=false;
        recurringPayments.add(new RecurringPayment("Rent", "11 of month", "255 $"));
        recurringPayments.add(new RecurringPayment("Car loan", "3rd of month", "300 $"));

        members.add(new Member("Dianne", "Sister", R.drawable.member_diane_kruger,"50"));
        members.add(new Member("Leo", "Brother", R.drawable.member_leonardo_dicaprio,"100"));

        expenses.add(new Expense("Example description", 52, "Lemne",1));
    }

    public boolean isEnabledAssistant() {
        return enabledAssistant;
    }

    public void setEnabledAssistant(boolean enabledAssistant) {
        this.enabledAssistant = enabledAssistant;
    }
}
