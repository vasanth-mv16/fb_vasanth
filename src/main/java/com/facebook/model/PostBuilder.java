package com.facebook.model;

import java.sql.Timestamp;

/**
 * <p>
 * Builder class for constructing object of the user
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class PostBuilder {

    private Post post;

    public PostBuilder() {
        this.post = new Post();
    }

    public void setUserId(final Long userId) {
        post.setUserId(userId);
    }

    public void setId(final Long id) {
        post.setId(id);
    }

    public void setCaption(final String caption) {
        post.setCaption(caption);
    }

    public void setLocation(final String location) {
        post.setLocation(location);
    }

    public void setUploadTime(final Timestamp uploadTime) {
        post.setUploadTime(uploadTime);
    }

    public Post buildPost() {
        return this.post;
    }
}
