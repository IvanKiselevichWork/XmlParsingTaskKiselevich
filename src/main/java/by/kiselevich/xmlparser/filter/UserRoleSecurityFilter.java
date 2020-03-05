package by.kiselevich.xmlparser.filter;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.UserRole;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
public class UserRoleSecurityFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger(UserRoleSecurityFilter.class);
    private static final int COOKIE_MAX_AGE_IN_SECONDS = 3 * 24 * 60 * 60;

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
            Cookie foundCookie = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Attribute.USER_ROLE.getValue())) {
                    userSet = userRepository.query(new FindUserByCookie(cookie.getValue().toCharArray()));
                    foundCookie = cookie;
                    break;
                }
            }
            if (userSet != null && !userSet.isEmpty()) {
                httpServletRequest.setAttribute(Attribute.USER_ROLE.getValue(), UserRole.USER);
                httpServletRequest.setAttribute(Attribute.LOGIN.getValue(), userSet.iterator().next().getLogin());

                // cookie auto renew
                foundCookie.setHttpOnly(true);
                foundCookie.setMaxAge(COOKIE_MAX_AGE_IN_SECONDS);
                ((HttpServletResponse)response).addCookie(foundCookie);
                LOG.trace("Cookie found, user found");
            } else {
                httpServletRequest.setAttribute(Attribute.USER_ROLE.getValue(), UserRole.GUEST);
                LOG.trace("Cookie found, user not found");
            }
        } else {
            httpServletRequest.setAttribute(Attribute.USER_ROLE.getValue(), UserRole.GUEST);
            LOG.trace("Cookie not found");
        }

        chain.doFilter(request, response);
    }
}
