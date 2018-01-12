package com.uid.team5.project.models;

import java.io.Serializable;

/**
 * Created by Tamas on 1/9/2018.
 */

public class Income implements Serializable {
    private String name;
    private String value;
    private String occurance;

    public Income(String name, String value, String occurance) {
        this.name = name;
        this.value = value;
        this.occurance = occurance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOccurance() {
        return occurance;
    }

    public void setOccurance(String occurance) {
        this.occurance = occurance;
    }
}
