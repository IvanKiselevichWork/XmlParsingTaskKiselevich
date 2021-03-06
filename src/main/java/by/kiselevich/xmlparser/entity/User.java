package by.kiselevich.xmlparser.entity;

import java.util.Arrays;
import java.util.stream.IntStream;

public class User {

    private String login;
    private char[] password;

    public User() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        char[] temp = new char[password.length];
        IntStream.range(0, password.length).forEach(i -> temp[i] = password[i]);
        return temp;
    }

    public void setPassword(char[] password) {
        this.password = new char[password.length];
        IntStream.range(0, password.length).forEach(i -> this.password[i] = password[i]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return Arrays.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
}
