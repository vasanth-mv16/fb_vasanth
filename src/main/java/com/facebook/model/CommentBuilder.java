package com.facebook.model;

/**
 * <p>
 * Builder class for constructing object of the comment
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class CommentBuilder {

    private Comment comment;

    public CommentBuilder() {
        this.comment = new Comment();
    }

    public void setId(final Long id) {
        comment.setId(id);
    }

    public void setUserId(final Long userId) {
        comment.setUserId(userId);
    }

    public void setPostId(final Long postId) {
        comment.setPostId(postId);
    }

    public void setMessage(final String message) {
        comment.setMessage(message);
    }

    public Comment build() {
        return this.comment;
    }
}
