package by.kiselevich.xmlparser.command;

public enum Attribute {
    MESSAGE("message"),
    MEDICINES("medicines"),
    FILES_NAMES_LIST("filesList"),
    USER_TYPE("userType"),
    LOGIN("login"),
    PARSER_TYPE("parserType");

    private String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
