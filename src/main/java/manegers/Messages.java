package manegers;

import java.util.ResourceBundle;

public class Messages {
    private Messages(){}
    private static final String BUNDLE_NAME = "locales.locale";
    private static final String EN = "en";
    private static final String UK  ="uk";
    private static final ResourceBundle MESSAGE_UK = ResourceBundle.getBundle(BUNDLE_NAME+"_"+ UK);
    private static final ResourceBundle MESSAGE_EN = ResourceBundle.getBundle(BUNDLE_NAME+"_"+EN);

    public static final String NAME_ERROR = "messages.nameError";
    public static final String SURNAME_ERROR = "messages.surnameError";
    public static final String EMAIL_ERROR = "messages.emailError";
    public static final String LOGIN_ERROR = "messages.loginError";
    public static final String PASS_INPUT_ERROR = "messages.passInputError";
    public static final String PASS_EQUAL_ERROR = "messages.passEqualError";
    public static final String SIGN_UP_ERROR = "messages.signUpError";
    public static final String LOG_IN_ERROR = "messages.logInError";
    public static final String BLOCKED_USER_ERROR = "messages.blockedUserError";


    public static final String INVALID_NAME_UK_ERROR = "messages.nameUkError";
    public static final String INVALID_NAME_EN_ERROR = "messages.nameEnError";
    public static final String ADDING_GENRE_ERROR = "messages.addingGenreError";
    public static final String ADDING_AUTHOR_ERROR = "messages.addingAuthorError";
    public static final String ADDING_EDITION_ERROR = "messages.addingEditionError";
    public static final String PRICE_ERROR = "messages.priceError";
    public static final String ID_ERROR = "messages.idError";

    public static final String GENRE_ADDED_SUCCESS = "messages.genreAddedSuccess";
    public static final String AUTHOR_ADDED_SUCCESS = "messages.authorAddedSuccess";
    public static final String EDITION_ADDED_SUCCESS = "messages.editionAddedSuccess";
    public static final String BALANCE_UPDATE_SUCCESS = "messages.balanceUpdateSuccess";


    public static final String BALANCE_INPUT_ERROR = "messages.balanceInputError";

    public static ResourceBundle getInstance(String locale) {

        if (locale == null || locale.equals(EN)) {
            return MESSAGE_EN;
        }
        return MESSAGE_UK;
    }

}
