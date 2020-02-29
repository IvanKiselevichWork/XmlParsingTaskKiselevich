package by.kiselevich.xmlparser.specification.cookie;

import by.kiselevich.xmlparser.entity.Cookie;
import by.kiselevich.xmlparser.repository.Repository;
import by.kiselevich.xmlparser.repository.cookie.CookieRepository;

import java.util.HashSet;
import java.util.Set;

public class FindCookie implements CookieSpecification {

    private Cookie cookie;

    public FindCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    @Override
    public Set<Cookie> query(Repository<Cookie> repository) {
        Set<Cookie> cookieSet = ((CookieRepository)repository).getAll();
        Set<Cookie> resultSet = new HashSet<>();
        if (cookieSet.contains(cookie)) {
            resultSet.add(cookie);
        }
        return resultSet;
    }
}
