package controller.commands.readers.order_editions;

import controller.commands.Command;
import manegers.Path;
import models.entity.Edition;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;



public class SortEditionsCommand implements Command {
    private static final String SORT_BY_AUTHOR = "author";
    private static final String SORT_BY_TITLE = "title";
    private static final String SORT_BY_PRICE = "price";
    private static final Logger LOGGER = Logger.getLogger(SortEditionsCommand.class);
    private final UserService userService = UserService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Edition> actualEditions = (List<Edition>) session.getAttribute("actualEditions");
        Optional<String> language = Optional.ofNullable((String) session.getAttribute("language"));
        Optional<String> sortBy = Optional.ofNullable(request.getParameter("sortBy"));

        if(!sortBy.isPresent()){
        LOGGER.info("Input is empty");
            return Path.MAIN_PAGE;
        }

        if(sortBy.get().equals(SORT_BY_PRICE)){
            actualEditions = userService.orderEditionsByPrice();
        }else if(sortBy.get().equals(SORT_BY_AUTHOR)){
            if(language.isPresent() && language.get().equals("uk")){
                actualEditions = userService.orderEditionsByAuthorNameUk();
            }else{
                actualEditions = userService.orderEditionsByAuthorNameEn();
            }
        }else if(sortBy.get().equals(SORT_BY_TITLE)){
            if(language.isPresent() && language.get().equals("uk")){
                actualEditions = userService.orderEditionsByTitleUk();
            }else{
                actualEditions = userService.orderEditionsByTitleEn();
            }
        }


        /*This part does not reconnect to DB*/
//        if(sortBy.get().equals(SORT_BY_PRICE)){
//            actualEditions.sort(Comparator.comparing(Edition::getPrice));
//         //   actualEditions.sort((ed1, ed2) -> ed1.getPrice().compareTo(ed2.getPrice()));
//        }else if(sortBy.get().equals(SORT_BY_AUTHOR)){
//            if(language.isPresent() && language.get().equals("uk")){
//                actualEditions.sort(Comparator.comparing(ed -> ed.getAuthor().getNameUk()));
//            }else{
//                actualEditions.sort(Comparator.comparing(ed -> ed.getAuthor().getNameEn()));
//            }
//        }else if(sortBy.get().equals(SORT_BY_TITLE)){
//            if(language.isPresent() && language.get().equals("uk")){
//                actualEditions.sort(Comparator.comparing(Edition::getTitleUk));
//            }else{
//                actualEditions.sort(Comparator.comparing(Edition::getTitleEn));
//            }
//        }

        session.setAttribute("actualEditions", actualEditions);
        return Path.MAIN_PAGE;
    }
}
