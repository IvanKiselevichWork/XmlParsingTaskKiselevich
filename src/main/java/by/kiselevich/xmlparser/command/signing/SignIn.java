package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.*;
import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;
import by.kiselevich.xmlparser.specification.user.FindUserByLoginAndPassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class SignIn implements Command {

    private static final String USER_NOT_FOUND = "User not found!";
    private static final String INVALID_LOGIN = "Login is invalid!";
    private static final String INVALID_PASSWORD = "Password is invalid!";

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
            req.setAttribute(Attribute.MESSAGE.getValue(), null);
            req.getSession().setAttribute(Attribute.USER_ROLE.getValue(), UserRole.USER);
            req.getSession().setAttribute(Attribute.LOGIN.getValue(), login);
            return Page.HOME;
        } else {
            req.setAttribute(Attribute.MESSAGE.getValue(), USER_NOT_FOUND);
            return Page.SIGN_IN_FORM;
        }
    }
}
