package controller.commands.readers.userCart;

import controller.commands.Command;
import manegers.Path;

import models.entity.ShoppingCart;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class RemoveFromCartCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RemoveFromCartCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("idRemEd"));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("priceEd")));

        ShoppingCart cartList = (ShoppingCart) session.getAttribute("cart");
        cartList.decreaseTotalSum(price);
        cartList.getEditions().removeIf(edition -> edition.getId() == id);
        session.setAttribute("cart", cartList);

        return Path.USER_CART_PAGE;
    }
}
