package ro.ducati.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Roshane on 9/13/2015.
 */
@Entity
public class Category {
    @Id
    private String label;

    public Category() {
    }

    public Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Category{" +
                "label='" + label + '\'' +
                '}';
    }
}
