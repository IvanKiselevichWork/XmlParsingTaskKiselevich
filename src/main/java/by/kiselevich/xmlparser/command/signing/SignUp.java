package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.*;
import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;
import by.kiselevich.xmlparser.specification.user.FindUserByLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements Command {

    private static final String LOGIN_IN_USE_MESSAGE = "Login in use!";
    private static final String INVALID_LOGIN = "Login is invalid!";
    private static final String INVALID_PASSWORD = "Password is invalid!";

    private UserRepository userRepository;

    public SignUp() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(Parameter.LOGIN.getValue());
        if (login == null || login.isEmpty()) {
            req.setAttribute(Attribute.MESSAGE.getValue(), INVALID_LOGIN);
            return Page.SIGN_UP_FORM;
        }
        String passwordString = req.getParameter(Parameter.PASSWORD.getValue());
        if (passwordString == null || passwordString.isEmpty()) {
            req.setAttribute(Attribute.MESSAGE.getValue(), INVALID_PASSWORD);
            return Page.SIGN_UP_FORM;
        }
        char[] password = passwordString.toCharArray();
        if (userRepository.query(new FindUserByLogin(login)).isEmpty()) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            userRepository.add(user);
            req.setAttribute(Attribute.MESSAGE.getValue(), null);
            req.getSession().setAttribute(Attribute.USER_ROLE.getValue(), UserRole.USER);
            req.getSession().setAttribute(Attribute.LOGIN.getValue(), login);
            return Page.HOME;
        } else {
            req.setAttribute(Attribute.MESSAGE.getValue(), LOGIN_IN_USE_MESSAGE);
            return Page.SIGN_UP_FORM;
        }
    }
}
