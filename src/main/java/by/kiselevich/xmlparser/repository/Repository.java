package by.kiselevich.xmlparser.repository;

public interface Repository<T> {
    void add(T t);

    void remove(T t);

    void update(T t);
}
