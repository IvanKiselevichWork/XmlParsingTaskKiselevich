package by.kiselevich.xmlparser.specification.cookie;

import by.kiselevich.xmlparser.entity.Cookie;
import by.kiselevich.xmlparser.repository.Repository;
import by.kiselevich.xmlparser.specification.Specification;

import java.util.Set;

public interface CookieSpecification extends Specification<Cookie> {
    Set<Cookie> query(Repository<Cookie> repository);
}
