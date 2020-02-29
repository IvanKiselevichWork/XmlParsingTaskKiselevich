package by.kiselevich.xmlparser.specification;

import by.kiselevich.xmlparser.repository.Repository;

import java.util.Set;

public interface Specification<T, S extends Repository<T>> {
    Set<T> query(S repository);
}
