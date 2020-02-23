package by.kiselevich.xmlparser.command;

public enum Attribute {
    ERROR_MESSAGE("error_message"),
    PARSED_XML("parsed_xml");

    private String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
