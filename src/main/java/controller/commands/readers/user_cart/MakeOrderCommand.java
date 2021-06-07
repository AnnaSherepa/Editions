package controller.commands.readers.user_cart;

import controller.commands.Command;
import manegers.Path;
import models.entity.ShoppingCart;
import models.entity.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MakeOrderCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(MakeOrderCommand.class);
    private final UserService userService = UserService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Start to order");
        HttpSession session = request.getSession();

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if(user.getBalance().compareTo(cart.getTotalSum()) < 0){
            LOGGER.info("User have not enough money. Please increase balance");
            return Path.USER_CART_PAGE;
        }
        userService.updateBalance(user.getId(), cart.getTotalSum().negate());
        if(!userService.setEditionsToUser(user.getId(), cart.getEditions())){
            LOGGER.info("Something goes wrong with db. Try later");
            return Path.USER_CART_PAGE;
        }

        session.setAttribute("user", userService.getUpdatedUser(user.getId()));
        cart.clear();
        session.setAttribute("cart", cart);
        LOGGER.info("Editions is ordered");

        return Path.USER_EDITION_PAGE;
    }
}
