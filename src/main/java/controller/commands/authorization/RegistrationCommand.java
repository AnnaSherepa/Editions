package controller.commands.authorization;

import controller.commands.Command;
import controller.commands.admin.CheckInput;
import manegers.Messages;
import manegers.Path;
import services.AuthorizationService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private AuthorizationService service = AuthorizationService.getInstance();
    private CheckInput check = CheckInput.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String) request.getSession().getAttribute("language");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        String repPassword = request.getParameter("pass_rep");

        request.setAttribute("login", login);
        request.setAttribute("email", email);

        boolean error = false;
        String page = null;

        if(!check.checkPersonalData(name)){
            request.setAttribute("nameError", Messages.getInstance(locale).getString(Messages.NAME_ERROR));
            error = true;
        }

        if(!check.checkPersonalData(surname)){
            request.setAttribute("surnameError", Messages.getInstance(locale).getString(Messages.SURNAME_ERROR));
            error = true;
        }
        if(!check.checkEmail(email)){
            request.setAttribute("emailError", Messages.getInstance(locale).getString(Messages.EMAIL_ERROR));
            error = true;
        }

        if(!check.checkLogin(login)){
            request.setAttribute("loginError", Messages.getInstance(locale).getString(Messages.LOGIN_ERROR));
            error = true;
        }

        if(!check.checkPass(password)){
            request.setAttribute("passInputError", Messages.getInstance(locale).getString(Messages.PASS_INPUT_ERROR));
            error = true;
        }

        if(!check.checkPassAndSecondPass(password, repPassword)){
            request.setAttribute("passEqualError", Messages.getInstance(locale).getString(Messages.PASS_EQUAL_ERROR));
            error = true;
        }

        if(error){
            page = Path.SIGN_UP;
        }else {
            if(!service.signUp(login, password, name, surname, email)){
                request.setAttribute("signUpError", Messages.getInstance(locale).getString(Messages.SIGN_UP_ERROR));
                page = Path.SIGN_UP;
            }else{
                page = Path.LOG_IN;
            }
        }

        return page;
    }
}
