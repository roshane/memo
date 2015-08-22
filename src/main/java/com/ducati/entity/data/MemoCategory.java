package com.ducati.entity.data;

import java.io.Serializable;

/**
 * Created by Roshane on 8/22/2015.
 */
public class MemoCategory implements Serializable{

    private String lable;
    private String description;

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MemoCategory{" +
                "lable='" + lable + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
