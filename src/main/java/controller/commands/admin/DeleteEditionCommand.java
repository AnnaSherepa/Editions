package controller.commands.admin;

import controller.commands.Command;
import manegers.Path;
import org.apache.log4j.Logger;
import services.AdminService;
import services.InitialService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEditionCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteEditionCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Deleting of edition");
        int id = Integer.parseInt(request.getParameter("idEdition"));
        if (AdminService.getInstance().deleteEditionById(id)){
            request.getServletContext().setAttribute("allEditions", InitialService.getInstance().allListOfEditions());
            LOGGER.info("Edition was deleted");
        }
        LOGGER.info("Finish delete command");
        return Path.MAIN_PAGE;
    }
}
