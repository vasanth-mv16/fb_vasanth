package com.facebook.model;

import java.sql.Timestamp;

/**
 * Represents a Post made by a user
 *
 * @version 1.0
 * @author vasanth
 */
public class Post {

    private Long userId;
    private Long id;
    private String caption;
    private String location;
    private Timestamp uploadTime;

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;

    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(final Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String toString() {
        return String.format("User Id =%d Post Id =%d Caption =%s  Location =%s  %s ",userId, id, caption, location,uploadTime);
    }
}
