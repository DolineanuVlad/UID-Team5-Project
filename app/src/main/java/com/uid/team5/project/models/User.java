package com.uid.team5.project.models;

import com.uid.team5.project.R;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Gabriel on 1/12/2018.
 */

public class User implements Serializable {

    private String email;
    private String password;
    private String name;
    private UUID id;
    private int groupId;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    private int avatar;

    public User(String email, String password, String name)
    {
        this.email = email;
        this.password = password;
        this.id = UUID.randomUUID();
        this.name = name;
        this.avatar = R.drawable.ic_avatar_person;
        groupId = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
