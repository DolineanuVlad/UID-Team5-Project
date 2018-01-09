package wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uid.team5.project.R;

import java.util.List;

/**
 * Created by mara.tatar on 1/5/2018.
 */

public class GoalAdapter extends BaseAdapter {
    private List<Goal> goals;
    private Context context;

    public GoalAdapter(List<Goal> goals, Context context) {
        this.goals = goals;
        this.context = context;
    }


    @Override
    public int getCount() {
        return goals.size();
    }

    @Override
    public Object getItem(int i) {
        return goals.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow = (view == null) ? inflater.inflate(R.layout.goal_item,viewGroup,false) : view;

        TextView goalName= (TextView) myRow.findViewById(R.id.goalName);
        TextView goalPriority = (TextView) myRow.findViewById(R.id.goalPriority);
        ProgressBar goalStatus = (ProgressBar) myRow.findViewById(R.id.goalStatus);

        goalName.setText(goals.get(position).getName());
        goalPriority.setText(String.valueOf(goals.get(position).getPriority()));
        goalStatus.setProgress(goals.get(position).getStatus());
        return myRow;
    }
}
