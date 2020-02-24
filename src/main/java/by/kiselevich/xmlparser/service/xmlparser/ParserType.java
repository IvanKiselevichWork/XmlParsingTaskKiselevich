package by.kiselevich.xmlparser.service.xmlparser;

public enum ParserType {
    DOM("dom"),
    SAX("sax"),
    STAX("stax");

    private String value;

    ParserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
