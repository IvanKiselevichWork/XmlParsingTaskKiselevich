package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.*;
import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;
import by.kiselevich.xmlparser.specification.user.FindUserByLoginAndPassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

import static by.kiselevich.xmlparser.util.HttpUtil.getLocalizedMessageFromResources;
import static by.kiselevich.xmlparser.util.HttpUtil.writeMessageToResponse;

public class SignIn implements Command {

    private static final String USER_NOT_FOUND_KEY = "home.user_not_found";
    private static final String INVALID_LOGIN_KEY = "home.invalid_login";
    private static final String INVALID_PASSWORD_KEY = "home.invalid_password";

    private UserRepository userRepository;

    public SignIn() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(Parameter.LOGIN.getValue());
        if (login == null || login.isEmpty()) {
            String message = getLocalizedMessageFromResources(req.getSession(), INVALID_LOGIN_KEY);
            writeMessageToResponse(resp, message);
            return null;
        }
        String passwordString = req.getParameter(Parameter.PASSWORD.getValue());
        if (passwordString == null || passwordString.isEmpty()) {
            String message = getLocalizedMessageFromResources(req.getSession(), INVALID_PASSWORD_KEY);
            writeMessageToResponse(resp, message);
            return null;
        }
        char[] password = passwordString.toCharArray();

        Set<User> foundUsers = userRepository.query(new FindUserByLoginAndPassword(login, password));
        if (!foundUsers.isEmpty()) {
            req.setAttribute(Attribute.MESSAGE.getValue(), null);
            req.getSession().setAttribute(Attribute.USER_ROLE.getValue(), UserRole.USER);
            req.getSession().setAttribute(Attribute.LOGIN.getValue(), login);
            return Page.HOME;
        } else {
            String message = getLocalizedMessageFromResources(req.getSession(), USER_NOT_FOUND_KEY);
            writeMessageToResponse(resp, message);
            return null;
        }
    }
}
