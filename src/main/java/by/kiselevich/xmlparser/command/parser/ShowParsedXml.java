package by.kiselevich.xmlparser.command.parser;

import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.view.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowParsedXml implements Command {
    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error_message", "xml not parsed, WIP");
        return Page.SHOW_PARSED_XML;
    }
}
