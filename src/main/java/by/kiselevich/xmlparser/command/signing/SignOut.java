package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Page;
import by.kiselevich.xmlparser.command.UserType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOut implements Command {

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Attribute.USER_TYPE.getValue())) {
                    cookie.setHttpOnly(true);
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
        req.setAttribute(Attribute.USER_TYPE.getValue(), UserType.GUEST);
        return Page.HOME;
    }
}
