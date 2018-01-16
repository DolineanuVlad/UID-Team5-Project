package com.uid.team5.project.adapters;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.models.Expense;
import com.uid.team5.project.models.ExpenseCategory;
import com.uid.team5.project.models.Member;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Gabriel on 1/11/2018.
 */

public class TransactionsAdapter extends BaseAdapter {

    ArrayList<Expense> mExpenses;
    ArrayList<ExpenseCategory> mExpensesCategories;
    Context mContext;
    LayoutInflater mInflater;
    public TransactionsAdapter(Context context, ArrayList<Expense> expenses, ArrayList<ExpenseCategory> expenseCategories) {
        mExpenses = expenses;
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mExpensesCategories = expenseCategories;

    }

    public ArrayList<Expense> getmExpenses() {
        return mExpenses;
    }

    public void setmExpenses(ArrayList<Expense> mExpenses) {
        this.mExpenses = mExpenses;
    }

    @Override
    public int getCount() {
        return mExpenses.size();
    }

    @Override
    public Object getItem(int i) {

        return mExpenses.get(i);
    }

    @Override
    public long getItemId(int i) {

        return mExpenses.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.transactions_list_item, viewGroup, false);
        Expense expense = mExpenses.get(i);

        TextView category = (TextView) rowView.findViewById(R.id.transactions_item_category);
        TextView amount = (TextView) rowView.findViewById(R.id.transactions_item_amount);
        TextView date = (TextView) rowView.findViewById(R.id.transactions_item_date);
        ImageView categoryImage = (ImageView) rowView.findViewById(R.id.transactions_item_category_image);

        TextView description = (TextView) rowView.findViewById(R.id.transactions_item_description);


        description.setText(expense.getDescription());

        String DATE_FORMAT_NOW = "EEE, d MMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String stringDate = sdf.format(expense.getDate());

        date.setText(stringDate);
        category.setText(mExpensesCategories.get(expense.getCategoryExpenseId()).getName());
        amount.setText(String.valueOf(expense.getPrice()));
        categoryImage.setImageResource(mExpensesCategories.get(expense.getCategoryExpenseId()).getIcon());

        return rowView;
    }
}
