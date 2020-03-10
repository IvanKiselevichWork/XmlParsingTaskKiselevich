package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.*;
import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;
import by.kiselevich.xmlparser.specification.user.FindUserByLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.kiselevich.xmlparser.util.HttpUtil.getLocalizedMessageFromResources;
import static by.kiselevich.xmlparser.util.HttpUtil.writeMessageToResponse;

public class SignUp implements Command {

    private static final String LOGIN_IN_USE_KEY = "login_in_use";
    private static final String INVALID_LOGIN_KEY = "invalid_login";
    private static final String INVALID_PASSWORD_KEY = "invalid_password";

    private UserRepository userRepository;

    public SignUp() {
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
            String message = getLocalizedMessageFromResources(req.getSession(), LOGIN_IN_USE_KEY);
            writeMessageToResponse(resp, message);
            return null;
        }
    }


}
