package wishlist;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by mara.tatar on 1/5/2018.
 */

public class Goal {
    private String Name;
    private String Priority;
    private int Status;
    private boolean isPersonal=true;
    private Bitmap image ;
    private String category;
    private double targetSum;
    private Date date;
    private String savingPlan;

    public Goal(){}
    public Goal(String name, String priority, int status, Bitmap image, String category, double targetSum, Date date, String savingPlan) {
        Name = name;
        Priority = priority;
        Status = status;
        this.image = image;
        this.category = category;
        this.targetSum = targetSum;
        this.date = date;
        this.savingPlan = savingPlan;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public void setPersonal(boolean personal) {
        isPersonal = personal;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTargetSum() {
        return targetSum;
    }

    public void setTargetSum(double targetSum) {
        this.targetSum = targetSum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSavingPlan() {
        return savingPlan;
    }

    public void setSavingPlan(String savingPlan) {
        this.savingPlan = savingPlan;
    }
}
