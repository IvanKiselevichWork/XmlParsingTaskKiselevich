package by.kiselevich.xmlparser.service.xmlparser.sax;

import by.kiselevich.xmlparser.entity.medicins.Medicines;
import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.service.xmlparser.XmlParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.*;
import java.io.IOException;

public class XmlSaxParser implements XmlParser {

    private static final Logger LOG = LogManager.getLogger(XmlSaxParser.class);

    // to prevent XXE attacks
    // see https://help.semmle.com/wiki/display/JAVA/Resolving+XML+external+entity+in+user-controlled+data
    private static final String SECURITY_FEATURE_URL = "http://apache.org/xml/features/disallow-doctype-decl";

    private SAXParser parser;

    public XmlSaxParser() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setFeature(SECURITY_FEATURE_URL, true);
            parser = factory.newSAXParser();
        } catch (SAXException | ParserConfigurationException e) {
            LOG.warn(e);
        }
    }

    @Override
    public Medicines parse(String xmlFilePath) {

        Medicines medicines = new Medicines();

        try {
            DefaultHandler handler = new SaxHandler(medicines);
            parser.parse(xmlFilePath, handler);
        } catch (IOException | SAXException | DatatypeConfigurationException e) {
            LOG.warn(e);
        }

        return medicines;
    }

    @Override
    public XmlParserType getType() {
        return XmlParserType.SAX;
    }
}
