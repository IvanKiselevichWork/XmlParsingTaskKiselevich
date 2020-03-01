package by.kiselevich.xmlparser.filter;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.UserType;
import by.kiselevich.xmlparser.entity.User;
import by.kiselevich.xmlparser.repository.user.UserRepository;
import by.kiselevich.xmlparser.repository.user.UserRepositoryImpl;
import by.kiselevich.xmlparser.specification.user.FindUserByCookie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
public class UserTypeSecurityFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger(UserTypeSecurityFilter.class);

    private UserRepository userRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userRepository = UserRepositoryImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            Set<User> userSet = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Attribute.USER_TYPE.getValue())) {
                    userSet = userRepository.query(new FindUserByCookie(cookie.getValue().toCharArray()));
                    break;
                }
            }
            if (userSet != null && !userSet.isEmpty()) {
                httpServletRequest.setAttribute(Attribute.USER_TYPE.getValue(), UserType.USER);
                httpServletRequest.setAttribute(Attribute.LOGIN.getValue(), userSet.iterator().next().getLogin());
                LOG.trace("Cookie found, user found");
            } else {
                httpServletRequest.setAttribute(Attribute.USER_TYPE.getValue(), UserType.GUEST);
                LOG.trace("Cookie found, user not found");
            }
        } else {
            httpServletRequest.setAttribute(Attribute.USER_TYPE.getValue(), UserType.GUEST);
            LOG.trace("Cookie not found");
        }

        chain.doFilter(request, response);
    }
}
