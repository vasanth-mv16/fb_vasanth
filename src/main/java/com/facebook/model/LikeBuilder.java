package com.facebook.model;

/**
 * <p>
 * Builder class for constructing object of the like
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class LikeBuilder {

    private Like like;

    public LikeBuilder() {
        this.like = new Like();
    }

    public void setId(final Long id) {
        like.setId(id);
    }

    public void setUserId(final Long userId) {
        like.setUserId(userId);
    }

    public void setPostId(final Long postId) {
        like.setPostId(postId);
    }

    public Like build() {
        return this.like;
    }
}
