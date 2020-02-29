package by.kiselevich.xmlparser.command.home;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Page;
import by.kiselevich.xmlparser.command.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowHomePage implements Command {

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(Attribute.USER_TYPE.getValue(), UserType.GUEST);
        return Page.HOME;
    }
}
