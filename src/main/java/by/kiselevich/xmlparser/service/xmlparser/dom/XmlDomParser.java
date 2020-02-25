package by.kiselevich.xmlparser.service.xmlparser.dom;

import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.service.xmlparser.XmlParserType;
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
    private static final String DELIMITER = " ";// "<br>";
    private static final String TABLE_START_TAG = "<table BORDER=10 BORDERCOLOR=RED >";
    private static final String TABLE_END_TAG = "</table>";
    private static final String ROW_START_TAG = "<tr>";
    private static final String ROW_END_TAG = "</tr>";
    private static final String HEADER_START_TAG = "<th>";
    private static final String HEADER_END_TAG = "</th>";
    private static final String DATA_START_TAG = "<td>";
    private static final String DATA_END_TAG = "</td>";

    private DocumentBuilder documentBuilder;

    public XmlDomParser() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.warn(e);
        }
    }

    @Override
    public String parse(String xmlFilePath) {

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
            result.append("<br><br>");

            //result.append(TABLE_START_TAG);

            NodeList children = root.getChildNodes();
            appendChildElements(children, result, "");

            //result.append(TABLE_END_TAG);

        } catch (IOException | SAXException e) {
            LOG.warn(e);
        }

        return result.toString();
    }

    @Override
    public XmlParserType getType() {
        return XmlParserType.DOM;
    }

    private void appendChildElements(NodeList root, StringBuilder result, String prefix) {
        //prefix = prefix + "--------";
        result.append(TABLE_START_TAG);

        Node child;
        String nodeName;
        for (int i = 0; i < root.getLength(); i++) {


            child = root.item(i);
            nodeName = child.getNodeName();
            if ("#text".equals(nodeName) || "#comment".equals(nodeName)) {
                continue;
            }
            result.append(ROW_START_TAG);
            result.append(DATA_START_TAG);
            //result.append(prefix);
            result.append(nodeName);
            result.append(DATA_END_TAG);
            result.append(DATA_START_TAG);
            result.append(child.getTextContent());

            if (child.hasChildNodes()) {
                appendChildElements(child.getChildNodes(), result, prefix);
            }
            result.append(DATA_END_TAG);

            result.append(ROW_END_TAG);
        }

        result.append(TABLE_END_TAG);
    }

}
