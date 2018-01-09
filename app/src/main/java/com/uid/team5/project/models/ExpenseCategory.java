package com.uid.team5.project.models;

/**
 * Created by Tamas on 1/9/2018.
 */

public class ExpenseCategory {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpenseCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
