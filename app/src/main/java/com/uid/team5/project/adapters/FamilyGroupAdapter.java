package com.uid.team5.project.adapters;

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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Gabriel on 1/7/2018.
 */

public class FamilyGroupAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Member> mDataSource;

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
        View rowView = mInflater.inflate(R.layout.family_member_list_item, viewGroup, false);
        Member member = mDataSource.get(i);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.family_group_list_item_name);
        TextView roleTextView = (TextView) rowView.findViewById(R.id.family_group_list_item_role);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.family_group_list_item_image);

        nameTextView.setText(member.getName());
        roleTextView.setText(member.getRole());
        imageView.setImageResource(member.getImageId());

        return rowView;
    }
}
