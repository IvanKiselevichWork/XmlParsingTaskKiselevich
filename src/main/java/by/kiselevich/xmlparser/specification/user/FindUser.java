package by.kiselevich.xmlparser.specification.user;

import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindUser implements UserSpecification {

    private String login;
    private char[] password;

    public FindUser(String login, char[] password) {
        this.login = login;
        this.password = Arrays.copyOf(password, password.length);
    }

    @Override
    public Set<User> query(UserRepository repository) {
        Set<User> userSet = repository.getAll();
        Set<User> resultSet = new HashSet<>();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        for (User userFromRepository : userSet) {
            if (user.equals(userFromRepository)) {
                resultSet.add(userFromRepository);
            }
        }
        return resultSet;
    }
}
