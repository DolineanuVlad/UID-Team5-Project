package com.uid.team5.project.models;

import com.uid.team5.project.R;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

/**
 * Created by vladdolineanuf on 03/01/2018.
 */

public class Expense implements Serializable{
    private String description;
    private float price;
    private String category;
    private int categoryPosition;

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    private int categoryImage;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

    public Expense(String description, float price, String category, int categoryPosition) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.categoryPosition = categoryPosition;
        this.categoryImage = R.drawable.ic_train_black_24dp;
        this.location = "";

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
