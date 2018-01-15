package com.uid.team5.project.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

    public AddExpensesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = (convertView == null) ? inflater.inflate(R.layout.list_element_manual_addition, parent, false) : convertView;

        if (pos < expenses.size()) {
            EditText description =  row.findViewById(R.id.add_expense_description);
            EditText price =  row.findViewById(R.id.add_expense_price);
            Spinner category = row.findViewById(R.id.add_expense_category);
            category.setSelection(expenses.get(pos).getCategoryPosition());
            description.setText(expenses.get(pos).getDescription());
            price.setText(new DecimalFormat("#.##").format(expenses.get(pos).getPrice()));
            final int position = pos;

            description.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                    expenses.get(position).setDescription(s.toString());
                    notifyDataSetChanged();
                }
            });

            price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                    expenses.get(position).setPrice(Float.parseFloat(s.toString()));
                    notifyDataSetChanged();
                }
            });

            category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    expenses.get(position).setCategoryPosition(pos);
                    String[] categories = parent.getResources().getStringArray(R.array.category_arrays);
                    expenses.get(position).setCategory(categories[pos]);
                    notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
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
        return expenses.get(position).hashCode();
    }

}
