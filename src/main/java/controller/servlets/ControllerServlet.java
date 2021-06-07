package controller.servlets;

import controller.commands.Command;
import org.apache.log4j.Logger;
import services.InitialService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ControllerServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(ControllerServlet.class);
    ControllerHelper controllerHelper = ControllerHelper.getInstance();
    InitialService initialService = InitialService.getInstance();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Init Controller Servlet");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        try {
            Command command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        if(page!=null) {
            response.sendRedirect(page);
            //RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            //dispatcher.forward(request, response);
        }else{
            System.out.println("Page is null, not founded");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
