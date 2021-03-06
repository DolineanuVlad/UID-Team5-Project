package com.uid.team5.project;

import android.content.Context;

import com.uid.team5.project.models.Expense;
import com.uid.team5.project.models.ExpenseCategory;
import com.uid.team5.project.models.Income;
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

public class AppDataSingleton implements Serializable {

    private static AppDataSingleton instance = null;
    public ArrayList<Expense> expenses;
    public ArrayList<Expense> currentInserionOfExpenses;
    private ArrayList<RecurringPayment> recurringPayments;
    private boolean enabledAssistant;
    private ArrayList<Member> members;
    private Expense CurrentlyEditted;
    private UUID currentUserId;
    private ArrayList<User> users;
    private ArrayList<Income> incomes;
    public int getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(int currentGroup) {
        this.currentGroup = currentGroup;
    }

    private int currentGroup;

    public ArrayList<Integer> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(ArrayList<Integer> groupsList) {
        this.groupsList = groupsList;
    }

    private ArrayList<Integer> groupsList;

    private ArrayList<ExpenseCategory> expenseCategories;

    public AppDataSingleton() {
        recurringPayments = new ArrayList<>();
        members = new ArrayList<>();
        expenses = new ArrayList<>();
        currentInserionOfExpenses = new ArrayList<>();
        users = new ArrayList<>();
        expenseCategories = new ArrayList<>();
        groupsList = new ArrayList<>();
        incomes=new ArrayList<>();
        enabledAssistant = false;

        if (expenses.size() == 0) {
            recurringPayments.add(new RecurringPayment("Rent", "11 of month", "255 $"));
            recurringPayments.add(new RecurringPayment("Car loan", "3rd of month", "300 $"));

            members.add(new Member("Dianne", "Sister", R.drawable.member_diane_kruger, "50"));
            members.add(new Member("Leo", "Brother", R.drawable.member_leonardo_dicaprio, "100"));

            User user = new User("test@email.com", "password", "test");
            users.add(user);

            expenses.add(new Expense(expenses.size(), "expense", 52, "Lemne", 1, user.getId()));
            expenses.add(new Expense(expenses.size(), "expense 2", 52, "Food", 1, user.getId()));

            expenseCategories.add(new ExpenseCategory(expenseCategories.size(),"Food","Category for all types of food", R.drawable.icons8_food_and_wine ));
            expenseCategories.add(new ExpenseCategory(expenseCategories.size(), "House", "Category for house related expenses", R.drawable.icons8_house_50));
            expenseCategories.add(new ExpenseCategory(expenseCategories.size(),"Personal Car","Car maintainance, gas", R.drawable.icons8_shopping_cart ));
            expenseCategories.add(new ExpenseCategory(expenseCategories.size(),"Children","Price for caring for children", R.drawable.icons8_baby ));
            expenseCategories.add(new ExpenseCategory(expenseCategories.size(),"Public Transport","Public transportation", R.drawable.icons8_transportation ));
            expenseCategories.add(new ExpenseCategory(expenseCategories.size(),"Vacation","Vacations", R.drawable.icons8_travel ));

            incomes.add(new Income("salary", "200", "weekly"));
        }

    }

    public static AppDataSingleton getInstance() {
        if (instance == null) {
            instance = new AppDataSingleton();
        }

        return instance;
    }

    public static void setInstance(AppDataSingleton mInstance) {
        instance = mInstance;
    }

    public static void loadFromFile(Context context) {
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

    public UUID getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(UUID currentUserId) {
        this.currentUserId = currentUserId;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
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

    public void fakeScanningRecipe() {
        currentInserionOfExpenses.clear();
        currentInserionOfExpenses.add(new Expense(expenses.size(), "Water", 2.5f, "Food", 0));
        currentInserionOfExpenses.add(new Expense(expenses.size(), "Bread", 3f, "Food", 0));
        currentInserionOfExpenses.add(new Expense(expenses.size(), "Coca Cola", 6.2f, "Food", 0));
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public Expense getCurrentlyEditted() {
        return CurrentlyEditted;
    }

    public void setCurrentlyEditted(Expense currentlyEditted) {
        CurrentlyEditted = currentlyEditted;
    }

    public ArrayList<ExpenseCategory> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(ArrayList<ExpenseCategory> expenseCategories) {
        this.expenseCategories = expenseCategories;
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

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public void setEnabledAssistant(boolean enabledAssistant) {
        this.enabledAssistant = enabledAssistant;
    }

    public ArrayList<Expense> getExpensesByCurrentUser() {

        ArrayList<Expense> usersExpenses = new ArrayList<>();
        for (Expense exp : expenses) {
            if (exp.getCreatedByUser().equals(currentUserId))
                usersExpenses.add(exp);
        }

        return usersExpenses;
    }

    public User getCurrentUser() {
        if (this.currentUserId != null) {
            for (User user : users) {
                if (user.getId().equals(this.currentUserId))
                    return user;
            }
        }

        return null;
    }
}
