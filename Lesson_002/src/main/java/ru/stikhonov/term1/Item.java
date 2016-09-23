package ru.stikhonov.term1;

import java.util.Date;

/**
 * @author Sergey Tikhonov
 */


public class Item {

    private String userName;
    private String description;
    private Date date;
    private String comments;
    private String itemID;


    public Item(String userName, String description, Date date, String comments) {
        this.userName = userName;
        this.description = description;
        this.date = date;
        this.comments = comments;
        this.itemID = itemStringIDGenerator();
    }

    public String getUserName() {
        return this.userName;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDate() {
        return this.date;
    }

    public String getComments() {
        return this.comments;
    }

    public String getItemID() {
        return this.itemID;
    }


    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    private String itemStringIDGenerator() {
        StringBuilder randomString = new StringBuilder();
        randomString.append("RQS").append((int) (Math.random() * 100000));
        return randomString.toString();
    }
}
