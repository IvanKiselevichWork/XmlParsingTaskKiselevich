package by.kiselevich.xmlparser.repository.user;

import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.Repository;
import by.kiselevich.xmlparser.specification.user.UserSpecification;

import java.util.Set;

public interface UserRepository extends Repository<User> {

    Set<User> getAll();

    Set<User> query(UserSpecification specification);
}
