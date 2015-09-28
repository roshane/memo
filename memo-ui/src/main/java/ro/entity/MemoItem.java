package ro.entity;

import java.util.Date;

/**
 * Created by Roshane on 9/11/2015.
 */
public class MemoItem {

    private Category category;
    private String shortDescription;
    private String description;
    private Date createDate;
    private Date lastModifiedDate;

    public MemoItem() {
        this.createDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "MemoItem{" +
                "category=" + category +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
