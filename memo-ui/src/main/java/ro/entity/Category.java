package ro.entity;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Roshane on 9/11/2015.
 */
public enum Category {
    JAVA("Java"),
    MAVEN("Maven"),
    SCALA("Scala"),
    C_SHARP("C#"),
    JAVA_SCRIPT("Java Script");

    private String lable;

    Category(String lable) {
        this.lable = lable;
    }

    public String getLable() {
        return lable;
    }

    public Category fromString(String lable) {
        return Arrays.asList(Category.values()).stream()
                .filter(category -> Objects.equals(category.lable, lable))
                .findFirst().get();
    }

    @Override
    public String toString() {
        return this.lable;
    }
}
