package by.kiselevich.xmlparser.command.parser;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.service.upload.FileUploader;
import by.kiselevich.xmlparser.view.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class ShowParsedXml implements Command {

    private FileUploader fileUploader;

    public ShowParsedXml() {
        fileUploader = new FileUploader();
    }


    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(Attribute.ERROR_MESSAGE.getValue(), "xml not parsed, WIP");
        List<File> fileList = fileUploader.uploadFiles(req);
        StringBuilder result = new StringBuilder();
        result.append("Uploaded files:").append("<br>");
        for(File file : fileList) {
            result.append(file.getAbsolutePath()).append("<br>");
        }
        req.setAttribute(Attribute.PARSED_XML.getValue(), result.toString());

        return Page.SHOW_PARSED_XML;
    }
}
