package by.kiselevich.xmlparser.service.xmlparser;

public interface XmlParser {
    String parse(String xmlFilePath);
    XmlParserType getType();
}
