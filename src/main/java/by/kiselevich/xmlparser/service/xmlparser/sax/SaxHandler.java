package by.kiselevich.xmlparser.service.xmlparser.sax;

import by.kiselevich.xmlparser.entity.medicins.Medicines;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {

    private Medicines medicines;

    public SaxHandler(Medicines medicines) {
        this.medicines = medicines;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }
}
