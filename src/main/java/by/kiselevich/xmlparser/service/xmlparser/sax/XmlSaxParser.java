package by.kiselevich.xmlparser.service.xmlparser.sax;

import by.kiselevich.xmlparser.entity.medicins.Medicines;
import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.service.xmlparser.XmlParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class XmlSaxParser implements XmlParser {

    private static final Logger LOG = LogManager.getLogger(XmlSaxParser.class);
    private static final String DELIMITER = "<br>";

    private XMLReader reader;
    private StringBuilder result;

    public XmlSaxParser() {
        try {
            reader = XMLReaderFactory.createXMLReader(); //todo
            //SAXParserFactory
            result = new StringBuilder();
            SaxHandler handler = new SaxHandler(result);
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOG.warn(e);
        }
    }

    @Override
    public Medicines parse(String xmlFilePath) {

        try {
            reader.parse(xmlFilePath);
        } catch (IOException | SAXException e) {
            LOG.warn(e);
        }

        //todo
        return new Medicines();
    }

    @Override
    public XmlParserType getType() {
        return XmlParserType.SAX;
    }
}
