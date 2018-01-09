package com.uid.team5.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.models.Income;

import java.util.List;

/**
 * Created by Tamas on 1/9/2018.
 */

public class IncomeAdapter extends BaseAdapter {
    private List<Income> incomes;
    private Context context;

    public IncomeAdapter(List<Income> incomes, Context context) {
        this.incomes = incomes;
        this.context = context;
    }


    @Override
    public int getCount() {
        return incomes.size();
    }

    @Override
    public Object getItem(int i) {
        return incomes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow = (view == null) ? inflater.inflate(R.layout.income_list_item,viewGroup,false) : view;

        TextView incomeName= (TextView) myRow.findViewById(R.id.incomeName);
        TextView incomeValue = (TextView) myRow.findViewById(R.id.incomeValue);
        TextView incomeOccurance = (TextView) myRow.findViewById(R.id.incomeOccurance);


        incomeName.setText(incomes.get(position).getName());
        incomeValue.setText(incomes.get(position).getValue());
        incomeOccurance.setText(incomes.get(position).getOccurance());
        return myRow;
    }
}
