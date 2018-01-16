package com.uid.team5.project.add_expenses;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.AddExpensesAdapter;
import com.uid.team5.project.adapters.ExpensesCategoriesSpinnerAdapter;
import com.uid.team5.project.models.Expense;

public class ManualAdditionActivity extends AppCompatActivity {

    ListView expensesList;
    Button cancelButton;
    AddExpensesAdapter adapter;
    AppDataSingleton dataService;
    AlertDialog.Builder builder;
    AlertDialog popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_addition);
        dataService = AppDataSingleton.getInstance();

        builder = new AlertDialog.Builder(this);
        popup = builder.create();
        View v = getLayoutInflater().inflate(R.layout.popup_expense_insertion, null);
        Spinner spinner = v.findViewById(R.id.new_expense_category);
        ExpensesCategoriesSpinnerAdapter ecsp = new ExpensesCategoriesSpinnerAdapter(dataService.getExpenseCategories(),this);
        spinner.setAdapter(ecsp);
        popup.setView(v);

        expensesList = findViewById(R.id.expense_list);
        cancelButton = findViewById(R.id.cancel_adding_expense);
        adapter = new AddExpensesAdapter(this);
        adapter.expenses = dataService.getCurrentInserionOfExpenses();

        expensesList.setAdapter(adapter);
    }

    public void onCancel(View v) {
        this.dataService.clearExpenses();
        this.onBackPressed();
    }

    public void onAddExpense(View w) {
        popup.show();
    }

    public void onExpenseAdded(View v) {
        TextView description = popup.findViewById(R.id.new_expense_description);
        TextView price = popup.findViewById(R.id.new_expense_price);
        Spinner category = popup.findViewById(R.id.new_expense_category);

        String descString = description.getText().toString();
        String priceString = price.getText().toString();

        if (descString.equals("") || priceString.equals("")) {
            popup.dismiss();
            return;
        }

        float priceFloat = Float.parseFloat(priceString);
        String categoryString = category.getSelectedItem().toString();
        int categoryPosition = (int)category.getSelectedItemId();



        dataService.currentInserionOfExpenses.add(new Expense(dataService.getExpenses().size(), descString, priceFloat, categoryString, categoryPosition));
        adapter.notifyDataSetChanged();

        description.setText(null);
        price.setText(null);
        category.setSelection(0);

        popup.dismiss();
    }

    public void onPopupCancel(View v) {
        popup.dismiss();
    }

    public void onConfirm(View v) {
        Toast toast = Toast.makeText(this, "Expenses were added successfully!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        this.dataService.getExpenses().addAll(this.dataService.getCurrentInserionOfExpenses());
        this.dataService.clearExpenses();
        this.finish();
    }
}
