package by.kiselevich.xmlparser.filter;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class UserRoleSecurityFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger(UserRoleSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpSession httpSession = httpServletRequest.getSession();

        Object userRoleObject = httpSession.getAttribute(Attribute.USER_ROLE.getValue());

        if (userRoleObject == null) {
            httpSession.setAttribute(Attribute.USER_ROLE.getValue(), UserRole.GUEST);
            LOG.trace("Set role to guest");
        }

        chain.doFilter(request, response);
    }
}
