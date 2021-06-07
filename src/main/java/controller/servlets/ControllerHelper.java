package controller.servlets;

import controller.commands.Command;
import controller.commands.admin.*;
import controller.commands.authorization.LogInCommand;
import controller.commands.authorization.LogOutCommand;
import controller.commands.authorization.RegistrationCommand;
import controller.commands.readers.AddMoneyCommand;
import controller.commands.readers.SwitchLangCommand;
import controller.commands.readers.orderEditions.GroupByGenreCommand;
import controller.commands.readers.orderEditions.SearchByTitleCommand;
import controller.commands.readers.orderEditions.SortEditionsCommand;
import controller.commands.readers.userCart.AddToCartCommand;
import controller.commands.readers.userCart.ClearCartCommand;
import controller.commands.readers.userCart.MakeOrderCommand;
import controller.commands.readers.userCart.RemoveFromCartCommand;
import controller.commands.readers.userEditions.DeleteFromUserEditions;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class ControllerHelper {

    private static ControllerHelper instance = null;
    private Map<String, Command> commands = new HashMap<>();

    private ControllerHelper() {
        //common command
        commands.put("logIn", new LogInCommand());
        commands.put("logOut", new LogOutCommand());
        commands.put("signUp", new RegistrationCommand());
        commands.put("mainPage", new RegistrationCommand());
        commands.put("switchLang", new SwitchLangCommand());

        //admin command
        commands.put("deleteEdition", new DeleteEditionCommand());
        commands.put("updateEdition", new UpdateEditionCommand());
        commands.put("editEdition", new EditEditionCommand());

        commands.put("newEdition", new NewEditionCommand());
        commands.put("saveNewEdition", new SaveNewEditionCommand());
        commands.put("saveNewGenre", new SaveNewGenreCommand());
        commands.put("saveNewAuthor", new SaveNewAuthorCommand());

        commands.put("usersList", new UsersListCommand());
        commands.put("switchUserStatus", new SwitchStatusCommand());
        commands.put("updateBalance", new AddMoneyCommand());

        commands.put("addToCart", new AddToCartCommand());
        commands.put("removeFromCart", new RemoveFromCartCommand());
        commands.put("clearCart", new ClearCartCommand());
        commands.put("makeOrderCart", new MakeOrderCommand());

        commands.put("groupByGenre", new GroupByGenreCommand());
        commands.put("sortEditions", new SortEditionsCommand());
        commands.put("searchByTitle", new SearchByTitleCommand());
        commands.put("deleteUserEdition", new DeleteFromUserEditions());



    }

    public Command getCommand(HttpServletRequest request) {
        Command command = commands.get(request.getParameter("command"));
        if(command == null){
            throw new NoSuchElementException();
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
