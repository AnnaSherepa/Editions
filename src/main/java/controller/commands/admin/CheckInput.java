package controller.commands.admin;

import models.entity.User;

public class CheckInput {
    private static CheckInput instance;
    private CheckInput(){}
    public static CheckInput getInstance() {
        if(instance == null){
            instance = new CheckInput();
        }
        return instance;
    }

    private static final String VALID_NAME_UK = "(?ui)[а-яії '-]+";
    private static final String VALID_NAME_EN = "(?ui)[a-z- ]+";
    private static final String VALID_PERSONAL_DATA = "([A-Z]|[А-ЯІЇЄ])(?ui)[a-zа-яії-]+";
    private static final String VALID_EMAIL = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
    private static final String VALID_PASS = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}";
    private static final String VALID_LOGIN = "(?=.*[A-Za-z\\d])[A-Za-z]+";


    /**
     * Method check correct name in uk by regex
     *
     * @param name - string in uk
     * @return boolean
     * */
    public boolean checkNameUk(String name){
        return name.matches(VALID_NAME_UK);
    }

    /**
     * Method check input of name in en by regex
     *
     * @param name - string in en
     * @return boolean
     * */
    public boolean checkNameEn(String name){
        return name.matches(VALID_NAME_EN);
    }

    /**
     * Method check correct input of name or surname by regex
     * @param data - parameter which represent name or surname
     * @return boolean
     */
    public boolean checkPersonalData(String data){
        return data.matches(VALID_PERSONAL_DATA);
    }

    /**
     * Method check correct input of login by regex
     * @param data - parameter which represent the login
     * @return boolean
     */
    public boolean checkLogin(String data){
        return data.matches(VALID_LOGIN);
    }

    /**
     * Method check correct email input by regex
     *
     * @param email - user input email
     * @return boolean
     * */
    public boolean checkEmail(String email){
        return email.matches(VALID_EMAIL);
    }

    /**
     * Method check input of password by regex
     *
     * @param pass - user password, first input
     * @return boolean
     * */
    public boolean checkPass(String pass){
        return pass.matches(VALID_PASS);
    }

    /**
     * Method compare two users input of password to ensure, that user know his password
     *
     * @param pass - first, main input of password
     * @param repPass - second password, which will be compared
     * @return boolean
     * */
    public boolean checkPassAndSecondPass(String pass, String repPass){
        return pass.equals(repPass);
    }


    public boolean checkStatus(User user){
        return user.getStatus() == 0;
    }
}
