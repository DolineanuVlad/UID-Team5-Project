package wishlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mara.tatar on 1/5/2018.
 */

public class GoalService {

    public static List<Goal> getGoals(){
        List<Goal> goals=new ArrayList<Goal>() ;

        Goal goal=new Goal();
        goal.setName("Iphone 10");
        goal.setPriority("High");
        goal.setStatus(50);
        goals.add(goal);

        Goal goal1=new Goal();
        goal1.setName("Harry Potter 1");
        goal1.setPriority("High");
        goal1.setStatus(80);
        goals.add(goal1);

        Goal goal2=new Goal();
        goal2.setName("Trip to Barcelona");
        goal2.setPriority("Medium");
        goal2.setStatus(30);
        goal2.setPersonal(false);
        goals.add(goal2);

        Goal goal3=new Goal();
        goal3.setName("Xmas Presents");
        goal3.setPriority("Medium");
        goal3.setStatus(80);
        goal3.setPersonal(false);
        goals.add(goal3);

        return goals;
    }
}
