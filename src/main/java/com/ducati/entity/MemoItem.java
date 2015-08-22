package com.ducati.entity;

import com.ducati.entity.data.MemoCategory;
import com.ducati.entity.data.MemoKeyword;

import java.util.Date;
import java.util.List;

/**
 * Created by Roshane on 8/22/2015.
 */
public class MemoItem {

    private MemoCategory category;
    private String id;
    private String content;
    private Date dateAdded;
    private Date dateLastModified;
    private List<MemoKeyword> keywordsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MemoCategory getCategory() {
        return category;
    }

    public void setCategory(MemoCategory category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public List<MemoKeyword> getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List<MemoKeyword> keywordsList) {
        this.keywordsList = keywordsList;
    }

    @Override
    public String toString() {
        return "MemoItem{" +
                "category=" + category +
                ", id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateLastModified=" + dateLastModified +
                ", keywordsList=" + keywordsList +
                '}';
    }

}
