package by.kiselevich.xmlparser.service.xmlparser.dom;

import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlDomParser implements XmlParser {

    private static final Logger LOG = LogManager.getLogger(XmlDomParser.class);
    private static final String DELIMITER = "<br>";

    private String xmlFilePath;
    private DocumentBuilder documentBuilder;

    public XmlDomParser() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.warn(e);
        }
    }

    public XmlDomParser(String xmlFilePath) {
        this();
        this.xmlFilePath = xmlFilePath;
    }

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public String parse() {

        StringBuilder result = new StringBuilder();
        result.append("Root element: ");
        Document document = null;
        try {
            document = documentBuilder.parse(xmlFilePath);
            Element root = document.getDocumentElement();
            result.append(root.getTagName()).append(DELIMITER);
            NamedNodeMap rootAttributes = root.getAttributes();
            result.append("Root attributes: ");
            Node rootAttribute;
            for (int i = 0; i < rootAttributes.getLength(); i++) {
                rootAttribute = rootAttributes.item(i);
                result.append(rootAttribute.getNodeName());
                result.append("=");
                result.append(rootAttribute.getNodeValue());
                result.append("; ");
            }
            result.append(DELIMITER);

            NodeList children = root.getChildNodes();
            appendChildElements(children, result, "");

        } catch (IOException | SAXException e) {
            LOG.warn(e);
        }

        return result.toString();
    }

    private void appendChildElements(NodeList root, StringBuilder result, String prefix) {
        prefix = prefix + "--------";
        Node child;
        String nodeName;
        for (int i = 0; i < root.getLength(); i++) {
            child = root.item(i);
            nodeName = child.getNodeName();
            if ("#text".equals(nodeName)) {
                continue;
            }
            result.append(prefix);
            result.append(nodeName);
            result.append(" : ").append(child.getTextContent()).append(DELIMITER);

            if (child.hasChildNodes()) {
                appendChildElements(child.getChildNodes(), result, prefix);
            }
        }
    }

}
