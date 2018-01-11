package com.uid.team5.project.add_expenses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.AddExpensesAdapter;
import com.uid.team5.project.models.Expense;

public class ManualAdditionActivity extends AppCompatActivity {

    ListView expensesList;
    Button cancelButton;
    AddExpensesAdapter adapter;
    AppDataSingleton dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_addition);
        dataService = AppDataSingleton.getInstance();
//        expensesList.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
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
        EditText descriptionText = findViewById(R.id.new_expense_description);
        String description = descriptionText.getText().toString();

        EditText priceText = findViewById(R.id.new_expense_price);
        float price = Float.parseFloat(priceText.getText().toString());

        Spinner categorySpinner = findViewById(R.id.new_expense_category);
        String category = categorySpinner.getSelectedItem().toString();
        int categoryPosition = categorySpinner.getSelectedItemPosition();

        dataService.currentInserionOfExpenses.add(new Expense(description, price, category, categoryPosition));
        adapter.notifyDataSetChanged();

        descriptionText.setText(null);
        priceText.setText(null);
        categorySpinner.setSelection(0);
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
