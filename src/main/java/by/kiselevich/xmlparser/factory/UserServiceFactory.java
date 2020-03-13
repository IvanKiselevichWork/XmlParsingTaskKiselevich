package by.kiselevich.xmlparser.factory;

import by.kiselevich.xmlparser.service.user.UserService;
import by.kiselevich.xmlparser.service.user.UserServiceImpl;

public class UserServiceFactory {

    private UserService userService;

    private UserServiceFactory(){
        userService = new UserServiceImpl();
    }

    private static class UserServiceFactoryHolder {
        private static final UserServiceFactory INSTANCE = new UserServiceFactory();
    }

    public static UserServiceFactory getInstance() {
        return UserServiceFactoryHolder.INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }
}
