package by.kiselevich.xmlparser.repository.user;

import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.specification.user.UserSpecification;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository {

    private Set<User> userSet;

    public UserRepositoryImpl() {
        userSet = ConcurrentHashMap.newKeySet();
    }

    @Override
    public void add(User user) {
        userSet.add(user);
    }

    @Override
    public void remove(User user) {
        userSet.remove(user);
    }

    @Override
    public void update(User user) {
        userSet.remove(user);
        userSet.add(user);
    }

    @Override
    public Set<User> getAll() {
        return new HashSet<>(userSet);
    }

    @Override
    public Set<User> query(UserSpecification specification) {
        return specification.query(this);
    }

}
