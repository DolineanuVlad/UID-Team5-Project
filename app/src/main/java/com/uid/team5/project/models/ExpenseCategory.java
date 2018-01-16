package com.uid.team5.project.models;

import java.io.Serializable;

/**
 * Created by Tamas on 1/9/2018.
 */

public class ExpenseCategory implements Serializable {
    private String name;
    private String description;
    private int id;
    private int icon;

    public ExpenseCategory(int id, String name, String description, int icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

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
}
