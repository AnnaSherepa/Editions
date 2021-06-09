package filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //initial method
        //do nothing
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();
        ServletContext context = request.getServletContext();

        LOGGER.info(session.getAttribute("role"));
        LOGGER.info(context.getAttribute("loggedUsers"));
        filterChain.doFilter(request,response);
    }


    @Override
    public void destroy() {
        //destroy method
        //do nothing
    }
}
