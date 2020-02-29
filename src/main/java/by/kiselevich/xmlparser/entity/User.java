package by.kiselevich.xmlparser.entity;

import java.util.stream.IntStream;

public class User {

    private String login;
    private char[] password;
    private char[] cookie;

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

    public char[] getCookie() {
        char[] temp = new char[cookie.length];
        IntStream.range(0, cookie.length).forEach(i -> temp[i] = cookie[i]);
        return temp;
    }

    public void setCookie(char[] cookie) {
        this.cookie = new char[cookie.length];
        IntStream.range(0, cookie.length).forEach(i -> this.cookie[i] = cookie[i]);
    }
}
