package com.uid.team5.project.adapters;

import android.app.AlertDialog;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.models.Member;
import com.uid.team5.project.shared.MainActivity;
import com.uid.team5.project.shared.drawer_fragments.income.IncomeFragment;


import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;


/**
 * Created by Gabriel on 1/7/2018.
 */

public class FamilyGroupAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Member> mDataSource;
    View rowView;
    public FamilyGroupAdapter(Context context, ArrayList<Member> members)
    {
        mContext = context;
        mDataSource = members;
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
        rowView = mInflater.inflate(R.layout.family_member_list_item, viewGroup, false);
        final Member member = mDataSource.get(i);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.family_group_list_item_name);
        TextView roleTextView = (TextView) rowView.findViewById(R.id.family_group_list_item_role);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.family_group_list_item_image);
        TextView limitation= (TextView) rowView.findViewById(R.id.family_group_list_item_limitation);
        ImageButton button=(ImageButton) rowView.findViewById(R.id.family_group_list_item_edit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPopup(view, member);
            }
        });
        nameTextView.setText(member.getName());
        roleTextView.setText(member.getRole());
        imageView.setImageResource(member.getImageId());
        limitation.setText(member.getLimitation());
        return rowView;
    }

    private void openPopup(View view, final Member member) {


        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog popup = builder.create();
        View optionsPopup = (View) mInflater.inflate(R.layout.poput_layout, null);
        final Button income = (Button) optionsPopup.findViewById(R.id.incomeB);
        Button budget = (Button) optionsPopup.findViewById(R.id.budgetB);
        Button cancelButton = (Button) optionsPopup.findViewById(R.id.cancelB);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup.dismiss();
                MainActivity activity = (MainActivity) mContext;
                Fragment myFragment = IncomeFragment.newInstance();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frame_layout, myFragment).addToBackStack(null).commit();
            }
        });
        budget.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               popup.dismiss();
               AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
               final AlertDialog popup2 = builder.create();
               final View setbudget = (View) mInflater.inflate(R.layout.setbudget, null);
               final Button save = (Button) setbudget.findViewById(R.id.save);
               Button cancelButton = (Button) setbudget.findViewById(R.id.cancelB);
               cancelButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       popup2.dismiss();
                   }
               });
               save.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       TextView newV=(TextView) setbudget.findViewById(R.id.newbudget);
                       member.setLimitation(newV.getText().toString());
                       notifyDataSetChanged();
                       popup2.dismiss();
                   }
               });
               popup2.setTitle(null);
               popup2.setView(setbudget);
               popup2.show();
            }
        });

        popup.setTitle(null);
        popup.setView(optionsPopup);
        popup.show();
    }
}
