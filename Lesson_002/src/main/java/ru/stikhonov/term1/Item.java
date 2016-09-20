package ru.stikhonov.term1;

import java.util.Date;

/**
 * @author Sergey Tikhonov
 */


public class Item {

    private String name;
    private String description;
    private Date date;
    private String comments;
    private String itemID;

    public Item(String name, String description, Date date, String comments) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.comments = comments;
        this.itemID = itemStringIDGenerator();
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

    public String getItemID() {
        return itemID;
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

    private String itemStringIDGenerator() {
        StringBuilder randomString = new StringBuilder();
        randomString.append("RQS").append((int) (Math.random() * 100000));
        return randomString.toString();
    }
}
