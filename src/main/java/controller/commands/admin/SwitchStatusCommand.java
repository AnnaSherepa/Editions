package controller.commands.admin;

import controller.commands.Command;
import manegers.Path;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SwitchStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int currentStatus = Integer.parseInt(request.getParameter("userStatus"));
        int newStatus = currentStatus == 0 ? 1 : 0;
        AdminService.getInstance().updateUserStatus(userId, newStatus);
        return "/Controller?command=usersList";
    }
}
