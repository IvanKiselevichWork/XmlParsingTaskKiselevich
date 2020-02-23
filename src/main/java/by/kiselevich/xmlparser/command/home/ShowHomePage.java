package by.kiselevich.xmlparser.command.home;

import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.view.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowHomePage implements Command {

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        return Page.HOME;
    }
}
