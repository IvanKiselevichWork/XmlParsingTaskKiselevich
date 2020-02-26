package by.kiselevich.xmlparser.service.xmlparser;

public enum TagType {
    INVALID_TAG("Invalid tag"),

    MEDICINE("Medicine"),

    ID("id"),
    NICKNAME("nickname"),

    NAME("Name"),
    PHARM("Pharm"),
    GROUP("Group"),
    ANALOGS("Analogs"),
    VERSIONS("Versions"),

    ANALOG_ID("AnalogId"),

    FORM("Form"),
    MANUFACTURER("Manufacturer"),
    CERTIFICATE("Certificate"),
    PACKAGE("Package"),
    DOSAGE("Dosage"),

    NUMBER("Number"),
    START_DATE("StartDate"),
    END_DATE("EndDate"),
    ORGANIZATION("Organization"),

    TYPE("Type"),
    AMOUNT("Amount"),
    PRICE("Price"),

    COUNT("Count"),
    PERIODICITY("Periodicity");

    private String value;

    TagType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
