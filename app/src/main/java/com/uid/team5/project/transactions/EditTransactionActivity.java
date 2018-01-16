package com.uid.team5.project.transactions;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.WelcomeActivity;
import com.uid.team5.project.adapters.ExpensesCategoriesSpinnerAdapter;
import com.uid.team5.project.auth.LoginActivity;
import com.uid.team5.project.models.Expense;
import com.uid.team5.project.shared.MainActivity;

import java.util.Calendar;
import java.util.Date;

public class EditTransactionActivity extends AppCompatActivity {

    AppDataSingleton dataService;

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataService = AppDataSingleton.getInstance();
        Expense expense = dataService.getCurrentlyEditted();

        setContentView(R.layout.activity_edit_transaction);

        Toolbar toolbar = findViewById(R.id.edit_transaction_toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageButton calendarBtn = findViewById(R.id.edit_transaction_calendar_button);
        final TextView dateLabel = findViewById(R.id.edit_transaction_date);
        final TextView amountLabel = findViewById(R.id.edit_transaction_amount_top);
        final TextView catLabel = findViewById(R.id.edit_transaction_category_top_label);
        final ImageView catTopIcon = findViewById(R.id.edit_transaction_category_icon);
        final EditText description = findViewById(R.id.edit_transaction_description);
        final Spinner categoriesSpinner = findViewById(R.id.edit_transaction_category_spinner);
        ExpensesCategoriesSpinnerAdapter ecsp = new ExpensesCategoriesSpinnerAdapter(dataService.getExpenseCategories(),this);
        categoriesSpinner.setAdapter(ecsp);
        final EditText amount = findViewById(R.id.edit_transaction_amount);

        FloatingActionButton ready = findViewById(R.id.floatingActionButton);

        Spinner spinner = findViewById(R.id.edit_transaction_repeat_spinner);

        if (expense != null) {
            description.setText(expense.getDescription());
            ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter

            int spinnerPosition = myAdap.getPosition("Every day");
            spinner.setSelection(spinnerPosition);
            amountLabel.setText(String.valueOf(expense.getPrice() + " USD"));
            amount.setText(String.valueOf(expense.getPrice()));

            String DATE_FORMAT_NOW = "EEE, d MMM yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            String stringDate = sdf.format(expense.getDate());
            dateLabel.setText(stringDate);
            catLabel.setText(dataService.getExpenseCategories().get(expense.getCategoryExpenseId()).getName());
            catTopIcon.setImageResource(dataService.getExpenseCategories().get(expense.getCategoryExpenseId()).getIcon());
            categoriesSpinner.setSelection(expense.getCategoryExpenseId());
        } else {
            expense = new Expense();
        }
        dataService.setCurrentlyEdittedTransaction(expense);

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                final int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        String DATE_FORMAT_NOW = "EEE, d MMM yyyy";
                        Date date = getDate(year, mMonth, dayOfMonth);
                        String stringDate = "";
                        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                        stringDate = sdf.format(date);
                        dateLabel.setText(stringDate);
                        dataService.getCurrentlyEditted().setDate(date);
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expense edditedExpense = dataService.getCurrentlyEditted();
                edditedExpense.setPrice(Float.valueOf(amount.getText().toString()));
                edditedExpense.setDescription(description.getText().toString());
                edditedExpense.setCategoryExpenseId((int)categoriesSpinner.getSelectedItemId());

                for(int i=0; i< dataService.getExpenses().size();i++)
                {
                    Expense exp = dataService.getExpenses().get(i);
                    if(exp.getId() == edditedExpense.getId())
                    {
                        dataService.getExpenses().remove(i);
                        dataService.getExpenses().add(i, dataService.getCurrentlyEditted());
                        break;
                    }
                }

                edditedExpense = null;
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_transactions_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_transactions_toolbar_remove) {

            Expense exp = dataService.getCurrentlyEditted();
            dataService.getExpenses().remove(exp);
            startActivity(new Intent(EditTransactionActivity.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
