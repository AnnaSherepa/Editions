package controller.commands.readers.user_cart;

import controller.commands.Command;
import manegers.Path;

import models.entity.ShoppingCart;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearCartCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClearCartCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        cart.clear();
        request.getSession().setAttribute("cart", cart);

        return Path.USER_CART_PAGE;
    }
}
