package com.facebook.view;

import com.facebook.controller.AuthenticationController;
import com.facebook.model.User;
import com.facebook.model.UserBuilder;
import com.facebook.view.validation.UserValidation;

/**
 * <p>
 * Manages the authentication view for user sign up, sign in
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class AuthenticationView extends CommonView {

    private final AuthenticationController authenticationController;
    private final UserValidation userValidation;
    private final UserView userView;
    private static AuthenticationView authenticationView;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private AuthenticationView() {
        userView = UserView.getInstance();
        authenticationController = AuthenticationController.getInstance();
        userValidation = UserValidation.getInstance();
    }

    /**
     * <p>
     * Gets the instance of the Authentication view
     * </p>
     *
     * @return Returns the instance of the Authentication view class.
     */
    public static AuthenticationView getInstance() {
        if (null == authenticationView) {
            authenticationView = new AuthenticationView();
        }

        return authenticationView;
    }

    /**
     * <p>
     * An enum with values signUp, signIn, exit
     * </p>
     */
    private enum UserOption {

        signUp(1), signIn(2), exit(3);
        final int choice;

        UserOption(final int choice) {
            this.choice = choice;
        }

        public static UserOption setUserOptions(final int choice) {
            for (final UserOption existingUserOptions : UserOption.values()) {

                if (existingUserOptions.choice == choice) {
                    return existingUserOptions;
                }
            }

            return null;
        }
    }

    /**
     * <p>
     * Collects and validates user options
     * </p>
     *
     * @return Returns {@link UserOption} of the user
     */
    private UserOption getOptions() {
        System.out.println("\tFACEBOOK\nCLICK 1 TO SIGN UP\nCLICK 2 TO SIGN IN\nCLICK 3 TO EXIT");
        final UserOption userOption = UserOption.setUserOptions(userView.getChoiceAndValidate());

        if (null != userOption) {
            return userOption;
        } else {
            System.out.println("INVALID USER GENDER, PLEASE SELECT THE ABOVE CHOICES");

            return getOptions();
        }
    }

    /**
     * <p>
     * Displays the menu details
     * </p>
     */
    public void displayMenu() {
        final UserBuilder user = new UserBuilder();

        switch (getOptions()) {
            case signUp:
                signUp(user);
                break;
            case signIn:
                signIn(user);
                break;
            case exit:
                System.exit(0);
            default:
                System.out.println("INVALID CHOICE");
                displayMenu();
        }
    }

    /**
     * <p>
     * Handles the sign-up process for a new user, collecting and validating user information
     * </p>
     *
     * @param user Refer the {@link User} for details
     */
    private void signUp(final UserBuilder user) {
        user.setId(userView.getUserIdGenerate());
        user.setName(userView.getNameAndValidate());
        user.setEmail(userView.getEmailAndValidate());
        user.setMobileNumber(userView.getMobileNumberAndValidate());
        user.setPassword(userView.getPasswordAndValidate());
        user.setGender(userView.getGenderAndValidate());
        user.setDateOfBirth(userView.getDateOfBirthAndValidate());

        if (authenticationController.signUp(user.build())) {
            System.out.println("ACCOUNT SUCCESSFULLY SIGN UP");
        } else {
            System.out.println("ACCOUNT ALREADY EXIST");
            displayMenu();
        }
        System.out.println("PRESS YES FOR EDIT USER DETAILS AND PRESS ANY KEY FOR MENU ");

        if (userValidation.validateAccess(SCANNER.nextLine())) {
            userView.displaysUserOptions(authenticationController.getUserId(user.build()));
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     * Handles the sign-in process for a user, collecting and validating user information, providing options for
     * user edit details
     * </p>
     *
     * @param user Refer the {@link User} for details
     */
    private void signIn(final UserBuilder user) {
        signInChoice(user);
        user.setPassword(userView.getPasswordAndValidate());

        if (authenticationController.signIn(user.build())) {
            System.out.println("ACCOUNT SIGN IN");
        } else {
            System.out.println("INCORRECT EMAIL OR MOBILE NUMBER AND PASSWORD");
            displayMenu();
        }
        System.out.println(String.join("", "DO YOU WANT TO EDIT,GET,DELETE THE USER DETAILS,PRESS 'YES' ",
                "FOR PRINT OPTION AND PRESS ANY KEY FOR MAIN MENU"));

        if (userValidation.validateAccess(SCANNER.nextLine())) {
            userView.displaysUserOptions(authenticationController.getUserId(user.build()));
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     * Collects the user's sign-in choice
     * </p>
     *
     * @param user Refer {@link User} to sign in
     */
    private void signInChoice(final UserBuilder user) {
        System.out.println("CLICK 1 TO MOBILE NUMBER\nCLICK 2 TO EMAIL ID");

        switch (userView.getChoiceAndValidate()) {
            case 1:
                user.setMobileNumber(userView.getMobileNumberAndValidate());
                break;
            case 2:
                user.setEmail(userView.getEmailAndValidate());
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT 1 OR 2");
                signInChoice(user);
        }
    }
}
