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
import com.uid.team5.project.models.User;

import java.util.ArrayList;

/**
 * Created by Gabriel on 1/16/2018.
 */

public class CreateGroupAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<User> mUsers;

    public CreateGroupAdapter(Context context, ArrayList<User> users)
    {
        mUsers = users;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return mUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.create_group_list_item, viewGroup, false);
        User user = mUsers.get(i);

        TextView name = (TextView) rowView.findViewById(R.id.create_group_user_name);
        TextView email = (TextView) rowView.findViewById(R.id.create_group_user_email);
        ImageView avatar = (ImageView) rowView.findViewById(R.id.create_group_user_icon);

        name.setText(user.getName());

        email.setText(user.getEmail());
        avatar.setImageResource(user.getAvatar());

        return rowView;
    }
}
