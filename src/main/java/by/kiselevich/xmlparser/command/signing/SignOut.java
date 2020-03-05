package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Page;
import by.kiselevich.xmlparser.command.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOut implements Command {

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(Attribute.USER_ROLE.getValue(), UserRole.GUEST);
        req.getSession().setAttribute(Attribute.LOGIN.getValue(), null);
        return Page.HOME;
    }
}
