package manegers;

public class Path {
    private Path(){}
    //main paths
    public static final String SIGN_UP = "/signUp";
    public static final String LOG_IN = "/logIn";
    public static final String MAIN_PAGE = "/index";
    public static final String ADMIN_ALL_USER_PAGE = "/usersList";
    public static final String ADMIN_NEW_EDITION_PAGE = "/newEdition";
    public static final String ADMIN_UPDATE_EDITION_PAGE = "/editEdition";

    public static final String USER_EDITION_PAGE = "/userEditions";
    public static final String USER_CART_PAGE = "/userCart";

    //commands
    public static final String ADMIN_USER_LIST = "/Controller?command=usersList";
    public static final String ADMIN_NEW_EDITION = "/Controller?command=newEdition";
    public static final String ADMIN_EDIT_EDITION = "/Controller?command=editEdition";
    public static final String ADMIN_SAVE_NEW_EDITION = "/Controller?command=saveNewEdition";
    public static final String ADMIN_SAVE_NEW_GENRE = "/Controller?command=saveNewGenre";
    public static final String ADMIN_SAVE_NEW_AUTHOR = "/Controller?command=saveNewAuthor";
    public static final String ADMIN_DELETE_EDITION = "/Controller?command=deleteEdition";
    public static final String ADMIN_UPDATE_EDITION = "/Controller?command=updateEdition";

    public static final String USER_UPDATE_BALANCE = "/Controller?command=updateBalance";
    public static final String USER_ADD_TO_CART = "/Controller?command=addToCart";
    public static final String USER_REMOVE_FROM_CART = "/Controller?command=removeFromCart";
    public static final String USER_CLEAR_CART = "/Controller?command=clearCart";
    public static final String USER_MAKE_ORDER_CART = "/Controller?command=makeOrderCart";

    public static final String USER_GROUP_BY_GENRE = "/Controller?command=groupByGenre";
    public static final String USER_SORT_EDITION = "/Controller?command=sortEditions";
    public static final String USER_SEARCH_BY_TITLE_EDITION = "/Controller?command=searchByTitle";
    public static final String USER_DELETE_EDITION_BY_ID = "/Controller?command=deleteUserEdition";





}

