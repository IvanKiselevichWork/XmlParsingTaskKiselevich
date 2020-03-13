package by.kiselevich.xmlparser.service.user;

import by.kiselevich.xmlparser.exception.UserServiceException;

public interface UserService {
    void singUp(String login, String password) throws UserServiceException;

    void singIn(String login, String password) throws UserServiceException;
}
