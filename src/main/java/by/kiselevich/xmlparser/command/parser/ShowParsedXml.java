package by.kiselevich.xmlparser.command.parser;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Parameter;
import by.kiselevich.xmlparser.entity.medicins.Medicines;
import by.kiselevich.xmlparser.service.upload.FileUploader;
import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.factory.XmlParserFactory;
import by.kiselevich.xmlparser.command.Page;

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

        List<File> fileList = fileUploader.uploadFiles(req);
        StringBuilder result = new StringBuilder();
        if (!fileList.isEmpty()) {
            result.append("Uploaded files:").append("<br>");
            for (File file : fileList) {
                result.append(file.getAbsolutePath()).append("<br>");
            }

            String type = req.getParameter(Parameter.PARSER_TYPE.getValue());
            XmlParser xmlParser = XmlParserFactory.getInstance().getParser(type);
            Medicines medicines = xmlParser.parse(fileList.get(0).getAbsolutePath());
            req.setAttribute(Attribute.MEDICINES.getValue(), medicines);
            result.append("Parser type: ").append(xmlParser.getType().getValue()).append("<br>");
        } else {
            result.append("No files uploaded").append("<br>");
        }

        req.setAttribute(Attribute.MESSAGE.getValue(), result.toString());

        return Page.SHOW_PARSED_XML;
    }

}
