package by.kiselevich.xmlparser.command;

public enum Parameter {
    COMMAND("command"),
    PARSER_TYPE("parser_type");

    private String value;

    Parameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}