package com.uid.team5.project.models;

/**
 * Created by vladdolineanuf on 03/01/2018.
 */

public class Expense {
    private String description;
    private float price;
    private String category;
    private int categoryPosition;

    public Expense(String description, float price, String category, int categoryPosition) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.categoryPosition = categoryPosition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryPosition() { return categoryPosition; }

    public void setCategoryPosition(int categoryPosition) { this.categoryPosition = categoryPosition; }
}
