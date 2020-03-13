package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.*;
import by.kiselevich.xmlparser.exception.UserServiceException;
import by.kiselevich.xmlparser.factory.UserServiceFactory;
import by.kiselevich.xmlparser.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.kiselevich.xmlparser.util.HttpUtil.getLocalizedMessageFromResources;
import static by.kiselevich.xmlparser.util.HttpUtil.writeMessageToResponse;

public class SignIn implements Command {

    private UserService userService;

    public SignIn() {
        userService = UserServiceFactory.getInstance().getUserService();
    }

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter(Parameter.LOGIN.getValue());
        String passwordString = req.getParameter(Parameter.PASSWORD.getValue());
        try {
            userService.singIn(login, passwordString);
            req.getSession().setAttribute(Attribute.USER_ROLE.getValue(), UserRole.USER);
            req.getSession().setAttribute(Attribute.LOGIN.getValue(), login);
            return Page.HOME;
        } catch (UserServiceException e) {
            String message = getLocalizedMessageFromResources(req.getSession(), e.getMessage());
            writeMessageToResponse(resp, message);
            return Page.EMPTY_PAGE;
        }
    }
}
