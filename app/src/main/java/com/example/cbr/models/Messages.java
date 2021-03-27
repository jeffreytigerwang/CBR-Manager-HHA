package com.example.cbr.models;

import java.util.Date;

public class Messages {
    private int userId;
    private String firstName;
    private String lastName;
    private String message;
    private Date postDate;

    public Messages(int userId, String firstName, String lastName, String message, Date postDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.postDate = postDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
