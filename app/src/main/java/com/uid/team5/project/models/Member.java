package com.uid.team5.project.models;

/**
 * Created by Gabriel on 1/5/2018.
 */

public class Member {

    public Member(String name, String role, int imageId)
    {
        this.name = name;
        this.role = role;
        this.imageId = imageId;
    }

   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String name;

    private String email;

    private String role;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private int imageId;

}
