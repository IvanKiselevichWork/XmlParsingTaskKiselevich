package by.kiselevich.xmlparser.command.parser;

import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowXmlUploadForm implements Command {
    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        return Page.XML_PARSER_UPLOAD_FORM;
    }
}
