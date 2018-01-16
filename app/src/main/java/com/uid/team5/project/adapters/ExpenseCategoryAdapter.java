package com.uid.team5.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.models.ExpenseCategory;

import java.util.List;

/**
 * Created by Tamas on 1/9/2018.
 */

public class ExpenseCategoryAdapter extends BaseAdapter {
    private List<ExpenseCategory> expenses;
    private Context context;

    public ExpenseCategoryAdapter(List<ExpenseCategory> expenses, Context context) {
        this.expenses = expenses;
        this.context = context;
    }


    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Object getItem(int i) {
        return expenses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow = (view == null) ? inflater.inflate(R.layout.expense_list_item,viewGroup,false) : view;

        TextView expenseName= (TextView) myRow.findViewById(R.id.expenseName);
        TextView expenseDescription = (TextView) myRow.findViewById(R.id.expenseDescription);
        ImageView expenseIcon=(ImageView) myRow.findViewById(R.id.expenseImage);

        expenseName.setText(expenses.get(position).getName());
        expenseDescription.setText(expenses.get(position).getDescription());
        expenseIcon.setImageResource(expenses.get(position).getIcon());
        return myRow;
    }
}

