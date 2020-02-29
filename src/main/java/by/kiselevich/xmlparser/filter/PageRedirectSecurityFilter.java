package by.kiselevich.xmlparser.filter;

import by.kiselevich.xmlparser.command.CommandName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/jsp/*" })
public class PageRedirectSecurityFilter implements Filter {

    private static final String COMMAND_PARAMETER_PREFIX = "/?command=";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + COMMAND_PARAMETER_PREFIX + CommandName.WRONG_REQUEST);
    }
}
