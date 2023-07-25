package com.facebook.model;

import com.facebook.model.User.Gender;

/**
 * <p>
 * Builder class for constructing object of the user
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class UserBuilder {
    private User user;

    public UserBuilder() {
        this.user = new User();
    }

    public void setId(final Long id) {
        user.setId(id);
    }

    public void setName(final String name) {
        user.setName(name);
    }

    public void setEmail(final String email) {
        user.setEmail(email);
    }

    public void setPassword(final String password) {
        user.setPassword(password);
    }

    public void setMobileNumber(final String mobileNumber) {
        user.setMobileNumber(mobileNumber);
    }

    public void setGender(final Gender gender) {
        user.setGender(gender);
    }

    public void setDateOfBirth(final String dateOfBirth) {
        user.setDateOfBirth(dateOfBirth);
    }

    public User build() {
        return this.user;
    }
}
