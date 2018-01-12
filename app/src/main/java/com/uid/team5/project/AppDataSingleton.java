package com.uid.team5.project;

import android.content.Context;

import com.uid.team5.project.models.Expense;
import com.uid.team5.project.models.Member;
import com.uid.team5.project.models.RecurringPayment;
import com.uid.team5.project.models.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Gabriel on 1/9/2018.
 */

public class AppDataSingleton implements Serializable{

    private static AppDataSingleton instance = null;
    public ArrayList<Expense> expenses;
    public ArrayList<Expense> currentInserionOfExpenses;
    private ArrayList<RecurringPayment> recurringPayments;
    private boolean enabledAssistant;
    private ArrayList<Member> members;
    private Expense CurrentlyEditted;

    public UUID getCurrentUserId() {
        return currentUserId;
    }

    private UUID currentUserId;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    private ArrayList<User> users;

    public AppDataSingleton()
    {
        recurringPayments = new ArrayList<>();
        members = new ArrayList<>();
        expenses = new ArrayList<>();
        currentInserionOfExpenses = new ArrayList<>();
        users = new ArrayList<>();

        enabledAssistant=false;
        //recurringPayments.add(new RecurringPayment("Rent", "11 of month", "255 $"));
        //recurringPayments.add(new RecurringPayment("Car loan", "3rd of month", "300 $"));

        //members.add(new Member("Dianne", "Sister", R.drawable.member_diane_kruger,"50"));
       // members.add(new Member("Leo", "Brother", R.drawable.member_leonardo_dicaprio,"100"));

        //expenses.add(new Expense(expenses.size(),"Example description", 52, "Lemne",1));
    }

    public static AppDataSingleton getInstance()
    {
        if(instance == null)
        {
            instance = new AppDataSingleton();
        }

        return instance;
    }

    public static void setInstance(AppDataSingleton mInstance)
    {
        instance = mInstance;
    }

    public static void loadFromFile(Context context)
    {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput("budget_data_save");
            ObjectInputStream is = null;
            is = new ObjectInputStream(fis);
            AppDataSingleton dataSingleton = (AppDataSingleton) is.readObject();
            AppDataSingleton.setInstance(dataSingleton);
            is.close();

            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(Context applicationContext) {
        FileOutputStream fos = null;
        try {
            fos = applicationContext.openFileOutput("budget_data_save", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(AppDataSingleton.getInstance());
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RecurringPayment> getRecurringPayments() {
        return recurringPayments;
    }

    public void setRecurringPayments(ArrayList<RecurringPayment> recurringPayments) {
        this.recurringPayments = recurringPayments;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public Expense getCurrentlyEditted() {
        return CurrentlyEditted;
    }

    public void setCurrentlyEdittedTransaction(Expense currentlyEditted) {
        CurrentlyEditted = currentlyEditted;
    }

    public ArrayList<Expense> getCurrentInserionOfExpenses() {
        return currentInserionOfExpenses;
    }

    public void setCurrentInserionOfExpenses(ArrayList<Expense> currentInserionOfExpenses) {
        this.currentInserionOfExpenses = currentInserionOfExpenses;
    }

    public void clearExpenses() {
        this.currentInserionOfExpenses = new ArrayList<>();
    }

    public boolean isEnabledAssistant() {
        return enabledAssistant;
    }

    public void setEnabledAssistant(boolean enabledAssistant) {
        this.enabledAssistant = enabledAssistant;
    }

    public void setCurrentUserId(UUID currentUserId) {
        this.currentUserId = currentUserId;
    }

    public ArrayList<Expense> getExpensesByCurrentUser() {

        ArrayList<Expense> usersExpenses = new ArrayList<>();
        for(Expense exp: expenses)
        {
            if(exp.getCreatedByUser().equals(currentUserId))
                usersExpenses.add(exp);
        }

        return usersExpenses;
    }
}
