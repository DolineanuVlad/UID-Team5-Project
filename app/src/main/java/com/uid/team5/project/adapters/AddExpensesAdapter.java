package com.uid.team5.project.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.models.Expense;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by vladdolineanuf on 03/01/2018.
 */

public class AddExpensesAdapter extends BaseAdapter {
    public ArrayList<Expense> expenses;
    private Context context;
    AppDataSingleton appDataSingleton;

    public AddExpensesAdapter(Context context) {
        this.context = context;
        appDataSingleton = AppDataSingleton.getInstance();
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = (convertView == null) ? inflater.inflate(R.layout.list_element_manual_addition, parent, false) : convertView;

        if (pos < expenses.size()) {
            final EditText description =  row.findViewById(R.id.add_expense_description);
            final EditText price =  row.findViewById(R.id.add_expense_price);
            final Spinner category = row.findViewById(R.id.add_expense_category);
            ExpensesCategoriesSpinnerAdapter ecsp = new ExpensesCategoriesSpinnerAdapter(appDataSingleton.getExpenseCategories(), context);
            category.setAdapter(ecsp);
            category.setSelection(expenses.get(pos).getCategoryExpenseId());



            description.setText(expenses.get(pos).getDescription());
            price.setText(new DecimalFormat("#.##").format(expenses.get(pos).getPrice()));

            description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        expenses.get(pos).setDescription(description.getText().toString());
                    }
                }
            });

            price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        expenses.get(pos).setPrice(Float.parseFloat(price.getText().toString()));;
                    }
                }
            });

            category.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        expenses.get(pos).setCategoryExpenseId((int)category.getSelectedItemId());
                        String[] categories = context.getResources().getStringArray(R.array.category_arrays);
                        expenses.get(pos).setCategory(categories[category.getSelectedItemPosition()]);
                        notifyDataSetChanged();
                    }
                }
            });
        }

        return row;
    }

    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Expense getItem(int position) {
        return expenses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
