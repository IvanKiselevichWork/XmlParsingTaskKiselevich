package by.kiselevich.xmlparser.command;

public enum UserType {
    GUEST,
    USER;

    public static UserType getUserType(String value) {
        UserType userType = null;
        try {
            userType = UserType.valueOf(value);
        } catch (IllegalArgumentException ignored) {
            userType = GUEST;
        }
        return userType;
    }
}
