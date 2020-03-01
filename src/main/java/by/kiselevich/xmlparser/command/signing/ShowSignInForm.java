package by.kiselevich.xmlparser.command.signing;

import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSignInForm implements Command {

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        return Page.SIGN_IN_FORM;
    }
}
