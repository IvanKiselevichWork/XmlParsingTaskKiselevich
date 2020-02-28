package by.kiselevich.xmlparser.command;

public enum Attribute {
    MESSAGE("message"),
    MEDICINES("medicines");

    private String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
