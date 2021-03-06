package by.kiselevich.xmlparser.factory;

import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.service.xmlparser.XmlParserType;
import by.kiselevich.xmlparser.service.xmlparser.dom.XmlDomParser;
import by.kiselevich.xmlparser.service.xmlparser.sax.XmlSaxParser;
import by.kiselevich.xmlparser.service.xmlparser.stax.XmlStaxParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

public class XmlParserFactory {

    private static final Logger LOG = LogManager.getLogger(XmlParserFactory.class);

    private static final XmlParserType DEFAULT_PARSER_TYPE = XmlParserType.DOM;

    private Map<XmlParserType, XmlParser> parsers;

    private XmlParserFactory() {
        parsers = new EnumMap<>(XmlParserType.class);
        parsers.put(XmlParserType.DOM, new XmlDomParser());
        parsers.put(XmlParserType.SAX, new XmlSaxParser());
        parsers.put(XmlParserType.STAX, new XmlStaxParser());
    }

    private static class ParserFactoryHolder {
        private static final XmlParserFactory INSTANCE = new XmlParserFactory();
    }

    public static XmlParserFactory getInstance() {
        return ParserFactoryHolder.INSTANCE;
    }

    public XmlParser getParser(String type) {
        XmlParserType xmlParserType;
        try {
            if (type != null) {
                xmlParserType = XmlParserType.valueOf(type.toUpperCase());
            } else {
                LOG.warn("parser type is null");
                xmlParserType = DEFAULT_PARSER_TYPE;
            }
        } catch (IllegalArgumentException e) {
            LOG.warn("wrong parser type");
            xmlParserType = DEFAULT_PARSER_TYPE;
        }
        return parsers.get(xmlParserType);
    }
}
