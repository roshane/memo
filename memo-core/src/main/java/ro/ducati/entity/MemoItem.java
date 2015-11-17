package ro.ducati.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Roshane on 9/13/2015.
 */
@Entity
public class MemoItem {

    @Id
    @GeneratedValue
    private long Id;
    private String category;
    private String shortDescription;
    private LocalDate dateAdded;
    private LocalDate dateModified;
    @Lob
    @Column(columnDefinition = "clob")
    private String content;

    public MemoItem() {
    }

    public MemoItem(String category, String shortDescription, LocalDate dateAdded, LocalDate dateModified, String content) {
        this.category = category;
        this.shortDescription = shortDescription;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.content = content;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    @Override
    public String toString() {
        return "MemoItem{" +
                "Id=" + Id +
                ", category='" + category + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                ", content='" + content + '\'' +
                '}';
    }

}
