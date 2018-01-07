package com.uid.team5.project.adapters;

/**
 * Created by Gabriel on 1/7/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.models.Member;

import java.util.ArrayList;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.models.Member;
import com.uid.team5.project.models.RecurringPayment;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Gabriel on 1/7/2018.
 */

public class RecurringPaymentAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<RecurringPayment> mDataSource;

    public RecurringPaymentAdapter(Context context, ArrayList<RecurringPayment> recurringPayments)
    {
        mContext = context;
        mDataSource = recurringPayments;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.recurring_payment_list_item, viewGroup, false);
        RecurringPayment recurringPayment = mDataSource.get(i);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.recurring_payment_list_item_name);
        TextView amountTextView = (TextView) rowView.findViewById(R.id.recurring_payment_list_item_amount);
        TextView dateView = (TextView) rowView.findViewById(R.id.recurring_payment_list_item_date);

        nameTextView.setText(recurringPayment.getName());
        amountTextView.setText(recurringPayment.getAmount());
        dateView.setText(recurringPayment.getDate());

        return rowView;
    }
}
