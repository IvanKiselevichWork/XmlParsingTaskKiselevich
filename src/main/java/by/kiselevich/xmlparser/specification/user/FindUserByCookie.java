package by.kiselevich.xmlparser.specification.user;

import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindUserByCookie implements UserSpecification {

    private char[] cookie;

    public FindUserByCookie(char[] cookie) {
        this.cookie = Arrays.copyOf(cookie, cookie.length);
    }

    @Override
    public Set<User> query(UserRepository repository) {
        Set<User> userSet = repository.getAll();
        Set<User> resultSet = new HashSet<>();
        for (User userFromRepository : userSet) {
            if (Arrays.equals(userFromRepository.getCookie(), cookie)) {
                resultSet.add(userFromRepository);
            }
        }
        return resultSet;
    }
}
