package by.kiselevich.xmlparser.command;

public enum Attribute {
    MESSAGE("message"),
    MEDICINES("medicines"),
    FILES_NAMES_LIST("filesList"),
    USER_ROLE("userRole"),
    LOGIN("login"),
    PARSER_TYPE("parserType"),
    IS_XML_VALID("isXmlValid"),
    LANGUAGE("language");

    private String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
