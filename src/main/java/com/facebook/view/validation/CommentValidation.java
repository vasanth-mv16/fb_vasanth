package com.facebook.view.validation;

/**
 * <p>
 * Given class used for validation the comment details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class CommentValidation {

    private static CommentValidation commentValidation;

    /**
     * <p>
     * Default constructor for comment validation
     * </p>
     */
    private CommentValidation() {}

    /**
     * <p>
     * Gets the instance of the comment validation
     * </p>
     *
     * @return Returns the singleton instance of the comment validation class
     */
    public static CommentValidation getInstance() {
        if(null == commentValidation) {
            commentValidation = new CommentValidation();
        }
        return commentValidation;
    }

    /**
     * <p>
     * Validates a comment id string using a regular expression pattern
     * </p>
     *
     * @param commentId The comment id to be validated
     * @return True if the comment id is valid, false otherwise
     */
    public boolean validateCommentId(final String commentId) {
        return commentId.matches("[\\d]");
    }
}
