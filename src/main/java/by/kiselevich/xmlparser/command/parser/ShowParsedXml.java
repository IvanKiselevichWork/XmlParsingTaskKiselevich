package by.kiselevich.xmlparser.command.parser;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Parameter;
import by.kiselevich.xmlparser.entity.medicins.Medicines;
import by.kiselevich.xmlparser.service.upload.FileUploader;
import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.factory.XmlParserFactory;
import by.kiselevich.xmlparser.command.Page;
import by.kiselevich.xmlparser.validator.XmlValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowParsedXml implements Command {

    private static final String INVALID_XML_KEY = "invalid_xml";
    private static final String NO_FILES_UPLOADED_KEY = "no_files";

    private FileUploader fileUploader;
    private XmlValidator xmlValidator;

    public ShowParsedXml() {
        fileUploader = new FileUploader();
        xmlValidator = new XmlValidator();
    }

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {

        List<File> fileList = fileUploader.uploadFiles(req);

        if (!fileList.isEmpty()) {
            List<String> uploadedFilesNames = new ArrayList<>();
            for (File file : fileList) {
                uploadedFilesNames.add(file.getAbsolutePath());
            }

            String xmlFilePath = fileList.get(0).getAbsolutePath();

            if (xmlValidator.isValid(xmlFilePath)) {
                String type = req.getParameter(Parameter.PARSER_TYPE.getValue());
                XmlParser xmlParser = XmlParserFactory.getInstance().getParser(type);
                Medicines medicines = xmlParser.parse(xmlFilePath);

                req.setAttribute(Attribute.IS_XML_VALID.getValue(), true);
                req.setAttribute(Attribute.FILES_NAMES_LIST.getValue(), uploadedFilesNames);
                req.setAttribute(Attribute.PARSER_TYPE.getValue(), xmlParser.getType().getValue());
                req.setAttribute(Attribute.MEDICINES.getValue(), medicines);
            } else {
                req.setAttribute(Attribute.IS_XML_VALID.getValue(), false);
                req.setAttribute(Attribute.MESSAGE.getValue(), INVALID_XML_KEY);
            }
        } else {
            req.setAttribute(Attribute.IS_XML_VALID.getValue(), false);
            req.setAttribute(Attribute.MESSAGE.getValue(), NO_FILES_UPLOADED_KEY);
        }

        return Page.SHOW_PARSED_XML;
    }

}
