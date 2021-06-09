package controller.commands.readers.user_cart;

import controller.commands.Command;
import manegers.Path;

import models.entity.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class RemoveFromCartCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("idRemEd"));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("priceEd")));
        String measEd = request.getParameter("measEd");

        ShoppingCart cartList = (ShoppingCart) session.getAttribute("cart");
        if(measEd.equals("UAH")){
            price = price.divide(BigDecimal.valueOf(30));
        }
        cartList.decreaseTotalSum(price);
        cartList.getEditions().removeIf(edition -> edition.getId() == id);
        session.setAttribute("cart", cartList);

        return Path.USER_CART_PAGE;
    }
}
