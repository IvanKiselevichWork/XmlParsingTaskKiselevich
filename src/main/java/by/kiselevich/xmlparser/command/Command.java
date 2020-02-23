package by.kiselevich.xmlparser.command;

import by.kiselevich.xmlparser.view.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Page execute(HttpServletRequest req, HttpServletResponse resp);
}
