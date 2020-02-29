package by.kiselevich.xmlparser.repository.cookie;

import by.kiselevich.xmlparser.entity.Cookie;
import by.kiselevich.xmlparser.specification.cookie.CookieSpecification;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CookieRepositoryImpl implements CookieRepository {

    private Set<Cookie> cookieSet;

    private CookieRepositoryImpl() {
        cookieSet = ConcurrentHashMap.newKeySet();
    }

    private static class CookieRepositoryImplHolder {
        private static final CookieRepositoryImpl INSTANCE = new CookieRepositoryImpl();
    }

    public static CookieRepositoryImpl getInstance() {
        return CookieRepositoryImplHolder.INSTANCE;
    }

    @Override
    public void add(Cookie cookie) {
        cookieSet.add(cookie);
    }

    @Override
    public void remove(Cookie cookie) {
        cookieSet.remove(cookie);
    }

    @Override
    public Set<Cookie> getAll() {
        return new HashSet<>(cookieSet);
    }

    @Override
    public Set<Cookie> query(CookieSpecification specification) {
        return specification.query(this);
    }

}
