package by.kiselevich.xmlparser.specification.user;

import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class FindUserByLogin implements UserSpecification {

    private String login;

    public FindUserByLogin(String login) {
        this.login = login;
    }

    @Override
    public Set<User> query(UserRepository repository) {
        Set<User> userSet = repository.getAll();
        Set<User> resultSet = new HashSet<>();
        for (User userFromRepository : userSet) {
            if (userFromRepository.getLogin().equals(login)) {
                resultSet.add(userFromRepository);
            }
        }
        return resultSet;
    }
}
