package listeners;

import org.apache.log4j.Logger;
import services.InitialService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;

public class ContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(ContextListener.class);

    private InitialService initialService = InitialService.getInstance();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Start of context Initialization");
        initStartParameters(sce.getServletContext());
        log.info("Finished successful");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Start of destroying");
        // nothing to do
        log.info("Finish of destroying");

    }

    /**
     * Getting initial data from db
     * */



    private void initStartParameters(ServletContext servletContext){
        log.info("Getting data from db started");

        servletContext.setAttribute("allEditions", initialService.allListOfEditions());

        servletContext.setAttribute("genres", initialService.allListOfGenres());
        servletContext.setAttribute("loggedUser", new HashSet<Integer>());
        log.info("Getting data from db, table genres finished");
        log.info("Getting data from db finished");

    }

}
