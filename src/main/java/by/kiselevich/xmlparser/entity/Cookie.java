package by.kiselevich.xmlparser.entity;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Cookie {
    private char[] value;

    public Cookie() {
    }

    public void setValue(char[] value) {
        this.value = new char[value.length];
        IntStream.range(0, value.length).forEach(i -> this.value[i] = value[i]);
    }

    public char[] getValue() {
        char[] temp = new char[value.length];
        IntStream.range(0, value.length).forEach(i -> temp[i] = value[i]);
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cookie cookie = (Cookie) o;

        return Arrays.equals(value, cookie.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
