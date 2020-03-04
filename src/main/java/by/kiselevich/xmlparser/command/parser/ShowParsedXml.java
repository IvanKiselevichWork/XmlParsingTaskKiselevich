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
import java.util.ArrayList;
import java.util.List;

public class ShowParsedXml implements Command {

    private FileUploader fileUploader;

    public ShowParsedXml() {
        fileUploader = new FileUploader();
    }

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {

        List<File> fileList = fileUploader.uploadFiles(req);

        if (!fileList.isEmpty()) {
            List<String> uploadedFilesNames = new ArrayList<>();
            for (File file : fileList) {
                uploadedFilesNames.add(file.getAbsolutePath());
            }

            String type = req.getParameter(Parameter.PARSER_TYPE.getValue());
            XmlParser xmlParser = XmlParserFactory.getInstance().getParser(type);
            Medicines medicines = xmlParser.parse(fileList.get(0).getAbsolutePath());

            req.setAttribute(Attribute.FILES_NAMES_LIST.getValue(), uploadedFilesNames);
            req.setAttribute(Attribute.PARSER_TYPE.getValue(), xmlParser.getType().getValue());
            req.setAttribute(Attribute.MEDICINES.getValue(), medicines);
        } else {
            req.setAttribute(Attribute.FILES_NAMES_LIST.getValue(), new ArrayList<String>());
        }

        return Page.SHOW_PARSED_XML;
    }

}
