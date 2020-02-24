package by.kiselevich.xmlparser.service.xmlparser;

public enum XmlParserType {
    DOM("dom");
    //SAX("sax"),
    //STAX("stax");

    private String value;

    XmlParserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
