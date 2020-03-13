package by.kiselevich.xmlparser.factory;

import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;

public class UserRepositoryFactory {

    private UserRepository userRepository;

    private UserRepositoryFactory(){
        userRepository = new UserRepositoryImpl();
    }

    private static class UserRepositoryFactoryHolder {
        private static final UserRepositoryFactory INSTANCE = new UserRepositoryFactory();
    }

    public static UserRepositoryFactory getInstance() {
        return UserRepositoryFactoryHolder.INSTANCE;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
