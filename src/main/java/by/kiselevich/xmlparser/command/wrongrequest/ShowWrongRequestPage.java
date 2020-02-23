package by.kiselevich.xmlparser.command.wrongrequest;

import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.view.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowWrongRequestPage implements Command {

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        return Page.WRONG_REQUEST;
    }
}
