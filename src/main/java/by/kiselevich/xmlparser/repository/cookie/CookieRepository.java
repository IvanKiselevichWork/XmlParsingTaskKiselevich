package by.kiselevich.xmlparser.repository.cookie;

import by.kiselevich.xmlparser.entity.Cookie;
import by.kiselevich.xmlparser.repository.Repository;
import by.kiselevich.xmlparser.specification.cookie.CookieSpecification;

import java.util.Set;

public interface CookieRepository extends Repository<Cookie> {

    Set<Cookie> getAll();

    Set<Cookie> query(CookieSpecification specification);
}
