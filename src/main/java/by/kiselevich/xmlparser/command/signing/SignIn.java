package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.*;
import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;
import by.kiselevich.xmlparser.specification.user.FindUserByLoginAndPassword;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class SignIn implements Command {

    private static final String USER_NOT_FOUND = "User not found!";
    private static final String INVALID_LOGIN = "Login is invalid!";
    private static final String INVALID_PASSWORD = "Password is invalid!";
    private static final int COOKIE_MAX_AGE_IN_SECONDS = 3 * 24 * 60 * 60;

    private UserRepository userRepository;

    public SignIn() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(Parameter.LOGIN.getValue());
        if (login == null || login.isEmpty()) {
            req.setAttribute(Attribute.MESSAGE.getValue(), INVALID_LOGIN);
            return Page.SIGN_IN_FORM;
        }
        String passwordString = req.getParameter(Parameter.PASSWORD.getValue());
        if (passwordString == null || passwordString.isEmpty()) {
            req.setAttribute(Attribute.MESSAGE.getValue(), INVALID_PASSWORD);
            return Page.SIGN_IN_FORM;
        }
        char[] password = passwordString.toCharArray();

        Set<User> foundUsers = userRepository.query(new FindUserByLoginAndPassword(login, password));
        if (!foundUsers.isEmpty()) {
            User user = foundUsers.iterator().next();
            req.setAttribute(Attribute.MESSAGE.getValue(), null);
            req.setAttribute(Attribute.USER_TYPE.getValue(), UserType.USER);
            req.setAttribute(Attribute.LOGIN.getValue(), login);
            Cookie cookie = new Cookie(Attribute.USER_TYPE.getValue(), String.valueOf(user.getCookie()));
            cookie.setHttpOnly(true);
            cookie.setMaxAge(COOKIE_MAX_AGE_IN_SECONDS);
            resp.addCookie(cookie);
            return Page.HOME;
        } else {
            req.setAttribute(Attribute.MESSAGE.getValue(), USER_NOT_FOUND);
            return Page.SIGN_IN_FORM;
        }
    }
}
