package com.uid.team5.project.models;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by vladdolineanuf on 03/01/2018.
 */

public class Expense implements Serializable {
    private String description;
    private float price;
    private String category;
    private int categoryExpenseId;
    private int id;
    private Date date;
    private int categoryImage;
    private String location;
    private UUID createdByUser;

    public Expense(int id, String description, float price, String category, int categoryPosition) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.categoryExpenseId = categoryPosition;
        this.categoryImage = R.drawable.ic_train_black_24dp;
        this.location = "";
        this.date = Calendar.getInstance().getTime();
        this.id = id;
        this.createdByUser = AppDataSingleton.getInstance().getCurrentUserId();
    }

    public Expense(int id, String description, float price, String category, int categoryPosition, UUID userId) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.categoryExpenseId = categoryPosition;
        this.categoryImage = R.drawable.ic_train_black_24dp;
        this.location = "";
        this.date = Calendar.getInstance().getTime();
        this.id = id;
        this.createdByUser = userId;
    }

    public Expense() {
    }

    public UUID getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(UUID createdByUser) {
        this.createdByUser = createdByUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public int getCategoryExpenseId() {
        return categoryExpenseId;
    }

    public void setCategoryExpenseId(int categoryExpenseId) {
        this.categoryExpenseId = categoryExpenseId;
    }
}
