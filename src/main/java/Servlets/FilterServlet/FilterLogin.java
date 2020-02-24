package Servlets.FilterServlet;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AFilter",
        urlPatterns = "/login")
public class FilterLogin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("role") != null) {
            if (session.getAttribute("role").equals("ADMIN")) {
                ((HttpServletResponse) response).sendRedirect("/admin");
            } else if (session.getAttribute("role").equals("USER")) {
                ((HttpServletResponse) response).sendRedirect("/user");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
