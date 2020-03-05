package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.*;
import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;
import by.kiselevich.xmlparser.specification.user.FindUserByLogin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUp implements Command {

    private static final Logger LOG = LogManager.getLogger(SignUp.class);

    private static final String LOGIN_IN_USE_MESSAGE = "Login in use!";
    private static final String INVALID_LOGIN = "Login is invalid!";
    private static final String INVALID_PASSWORD = "Password is invalid!";

    private static final String TEXT_CONTENT_TYPE = "text/plain";
    private static final String UTF_8_ENCODING = "UTF-8";

    private UserRepository userRepository;

    public SignUp() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(Parameter.LOGIN.getValue());
        if (login == null || login.isEmpty()) {
            writeMessageToResponse(resp, INVALID_LOGIN);
            return null;
        }
        String passwordString = req.getParameter(Parameter.PASSWORD.getValue());
        if (passwordString == null || passwordString.isEmpty()) {
            writeMessageToResponse(resp, INVALID_PASSWORD);
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
            writeMessageToResponse(resp, LOGIN_IN_USE_MESSAGE);
            return null;
        }
    }

    private void writeMessageToResponse(HttpServletResponse resp, String message) {
        try {
            resp.setContentType(TEXT_CONTENT_TYPE);  // Set content type of the response so that jQuery knows what it can expect.
            resp.setCharacterEncoding(UTF_8_ENCODING); // You want world domination, huh?
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(message);
        } catch (IOException e) {
            LOG.warn(e);
        }
    }
}
