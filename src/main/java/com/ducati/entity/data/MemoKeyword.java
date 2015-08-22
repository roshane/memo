package com.ducati.entity.data;

/**
 * Created by Roshane on 8/22/2015.
 */
public class MemoKeyword {
    private String label;
    private String description;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MomoKeywords{" +
                "label='" + label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
