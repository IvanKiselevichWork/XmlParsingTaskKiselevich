package by.kiselevich.xmlparser.service.xmlparser;

import by.kiselevich.xmlparser.entity.medicins.Medicines;

public interface XmlParser {
    Medicines parse(String xmlFilePath);
    XmlParserType getType();
}
