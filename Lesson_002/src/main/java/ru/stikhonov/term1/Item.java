package ru.stikhonov.term1;

import java.sql.Time;
import java.util.Date;

/**
 * @author Sergey Tikhonov
 */
public class Item {
    private String name;
    private String description;
    private Date date;
    private String comments;

    public Item(String name, String description, Date date, String comments) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
