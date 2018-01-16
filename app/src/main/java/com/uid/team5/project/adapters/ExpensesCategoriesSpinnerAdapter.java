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
 * Created by Gabriel on 1/16/2018.
 */

public class ExpensesCategoriesSpinnerAdapter extends BaseAdapter {
    private List<ExpenseCategory> expenseCategories;
    private Context context;

    public ExpensesCategoriesSpinnerAdapter(List<ExpenseCategory> expensesCategories, Context context) {
        this.expenseCategories = expensesCategories;
        this.context = context;
    }


    @Override
    public int getCount() {
        return expenseCategories.size();
    }

    @Override
    public Object getItem(int i) {
        return expenseCategories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return expenseCategories.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow = (view == null) ? inflater.inflate(R.layout.expense_category_spinner_item,viewGroup,false) : view;

        TextView expenseCatName= (TextView) myRow.findViewById(R.id.expense_category_spinne_item_label);
        ImageView expenseCatImage = (ImageView) myRow.findViewById(R.id.expense_category_spinne_item_image);


        expenseCatName.setText(expenseCategories.get(position).getName());
        expenseCatImage.setImageResource(expenseCategories.get(position).getIcon());
        return myRow;
    }
}
