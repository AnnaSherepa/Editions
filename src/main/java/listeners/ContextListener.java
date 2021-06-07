package listeners;

import models.entity.Edition;
import models.entity.User;
import org.apache.log4j.Logger;
import services.InitialService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class ContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(ContextListener.class);

    private InitialService initialService = InitialService.getInstance();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Start of context Initialization");
      //  initI18N(sce.getServletContext());
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

    /**
     * Initializes i18n subsystem.
     */
    private void initI18N(ServletContext servletContext) {
        log.info("I18N subsystem initialization started");

        String localesValue = servletContext.getInitParameter("locales");
        if (localesValue == null || localesValue.isEmpty()) {
            log.warn("'locales' init parameter is empty, the default encoding will be used");
        } else {
            List<String> locales = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(localesValue);
            while (st.hasMoreTokens()) {
                String localeName = st.nextToken();
                locales.add(localeName);
            }

            log.debug("Application attribute set: locales --> " + locales);
            servletContext.setAttribute("locales", locales);
        }

        log.debug("I18N subsystem initialization finished");
    }
}
