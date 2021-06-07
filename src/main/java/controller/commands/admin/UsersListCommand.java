package controller.commands.admin;

import controller.commands.Command;
import manegers.Path;
import manegers.ProjectConstants;
import models.entity.User;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UsersListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int users = ProjectConstants.TOTAL_NUM_USERS_IN_ONE_PAGE;

        Integer numOfPages = (Integer) request.getSession().getAttribute("numOfPages");
        String reqPage =   Optional.ofNullable(request.getParameter("page")).orElse("1");
        int currentPage = Integer.parseInt(reqPage);

        if(!Optional.ofNullable(numOfPages).isPresent()){
            int sizeOfTableUsers = AdminService.getInstance().getSizeTableUsers();
            int ost = sizeOfTableUsers % users;
            numOfPages = ost == 0 ? sizeOfTableUsers/users : sizeOfTableUsers/users + 1;
            request.getSession().setAttribute("numOfPages", numOfPages);
        }
        List<User> allUsers = AdminService.getInstance().getAllUsers(users, users*(currentPage-1));

        request.getSession().setAttribute("allUsers", allUsers);

        return Path.ADMIN_ALL_USER_PAGE;
    }
}
