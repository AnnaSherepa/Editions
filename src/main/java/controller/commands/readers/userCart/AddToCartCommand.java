package controller.commands.readers.userCart;

import controller.commands.Command;
import manegers.Path;
import models.entity.Edition;
import models.entity.ShoppingCart;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class AddToCartCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddToCartCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();


        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
        if(shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        LOGGER.info("Cart list initialized");
        List<Edition> editions = (List<Edition>) servletContext.getAttribute("allEditions");
        LOGGER.info("All editions is gotten");
        LOGGER.info(request.getParameter("orderedIdEdition"));
        int idEdition = Integer.parseInt(request.getParameter("orderedIdEdition"));
        LOGGER.info("Edition ID: " + idEdition);
        Edition edition = editions.stream().filter(edition1 -> idEdition == edition1.getId()).findAny().orElse(null);

        shoppingCart.addEdition(edition);
        session.setAttribute("cart", shoppingCart);
        return Path.MAIN_PAGE;
    }
}
