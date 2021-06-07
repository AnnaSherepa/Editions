package controller.commands.readers;

import controller.commands.Command;
import manegers.Messages;
import manegers.Path;

import models.entity.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class AddMoneyCommand implements Command {
    private static final UserService userService = UserService.getInstance();
    private static final Logger LOGGER = Logger.getLogger(AddMoneyCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Start to update users balance");
        String balance = request.getParameter("newBalance");

        int id = Integer.parseInt(request.getParameter("idUser"));
        boolean error = false;
        if(!userService.checkBalance(balance)){
            request.getSession().setAttribute("balanceError", Messages.BALANCE_INPUT_ERROR);
            LOGGER.info("Balance input incorrect");
            error = true;
        }

        BigDecimal newBalance = BigDecimal.valueOf(Double.parseDouble(balance));
        if(newBalance.compareTo(BigDecimal.valueOf(0.0)) < 0){
            LOGGER.info("Balance is negative");
            request.getSession().setAttribute("balanceError", Messages.BALANCE_INPUT_ERROR);
            error = true;
        }

        User user = (User) request.getSession().getAttribute("user");
        user.setBalance(user.getBalance().add(newBalance));
        if(!error){
            if(!userService.updateBalance(id, newBalance)){
                LOGGER.info("Something wrong  with db updating");
            }else{
                request.getSession().setAttribute("user", user);

            }
        }
        LOGGER.info("Updating db is finished");

        /*Have to update info  about user! How?*/
        return Path.MAIN_PAGE;
    }
}
