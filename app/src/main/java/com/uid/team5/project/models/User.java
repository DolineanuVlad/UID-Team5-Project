package com.uid.team5.project.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Gabriel on 1/12/2018.
 */

public class User implements Serializable {

    private String email;
    private String password;
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
        this.id = UUID.randomUUID();
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
