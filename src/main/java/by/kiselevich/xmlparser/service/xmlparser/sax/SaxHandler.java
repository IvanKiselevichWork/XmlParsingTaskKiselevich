package by.kiselevich.xmlparser.service.xmlparser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {

    private StringBuilder result;

    public SaxHandler(StringBuilder result) {
        this.result = result;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        result.append(localName).append(" ");
        for (int i = 0; i < attributes.getLength(); i++) {
            result.append(attributes.getLocalName(i)).append("=").append(attributes.getValue(i));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length);
        result.append(text);
        result.append("<br>");
    }
}
