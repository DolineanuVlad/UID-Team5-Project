package com.uid.team5.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.models.Expense;
import com.uid.team5.project.models.Member;

import java.util.ArrayList;

/**
 * Created by Gabriel on 1/11/2018.
 */

public class TransactionsAdapter extends BaseAdapter {

    public ArrayList<Expense> getmExpenses() {
        return mExpenses;
    }

    public void setmExpenses(ArrayList<Expense> mExpenses) {
        this.mExpenses = mExpenses;
    }

    ArrayList<Expense> mExpenses;
    Context mContext;
    LayoutInflater mInflater;

    public TransactionsAdapter(Context context, ArrayList<Expense> expenses)
    {
        mExpenses = expenses;
        mContext = context;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount()
    {
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
        ImageView categoryImage = (ImageView) rowView.findViewById(R.id.transactions_item_category_image);
        TextView description = (TextView) rowView.findViewById(R.id.transactions_item_description);
        ImageView descriptionImage = (ImageView) rowView.findViewById(R.id.transactions_item_descript_image);

        TextView location = (TextView) rowView.findViewById(R.id.transactions_item_location);
        ImageView locationImage = (ImageView) rowView.findViewById(R.id.transactions_item_loc_image);

        if(!expense.getDescription().equals(""))
        {
            description.setVisibility(View.VISIBLE);
            description.setText(expense.getDescription());
            descriptionImage.setVisibility(View.VISIBLE);
        }

        if(!expense.getLocation().equals(""))
        {
            location.setVisibility(View.VISIBLE);
            location.setText(expense.getLocation());
            locationImage.setVisibility(View.VISIBLE);
        }

        category.setText(expense.getCategory());
        amount.setText(String.valueOf(expense.getPrice()));
        categoryImage.setImageResource(expense.getCategoryImage());

        return rowView;
    }
}
