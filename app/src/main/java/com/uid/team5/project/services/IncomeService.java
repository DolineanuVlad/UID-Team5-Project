package com.uid.team5.project.services;

import com.uid.team5.project.models.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tamas on 1/9/2018.
 */

public class IncomeService {
    public static List<Income> getExpenses(){
        List<Income> expenses=new ArrayList<Income>();

        Income e1=new Income("salary", "2000", "monthly");
        Income e2=new Income("rent", "20", "weekly");

        expenses.add(e1);
        expenses.add(e2);

        return expenses;
    }
}
