package Servlets.FilterServlet;

import Model.User;
import Service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter",
        urlPatterns = "/auth")
public class FilterAuth implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserServiceImpl userService = UserServiceImpl.getInstance();
        User user;

        if (session != null && session.getAttribute("role") != null) {
            if (session.getAttribute("role").equals("ADMIN")) {
                ((HttpServletResponse) response).sendRedirect("/admin");
            } else if (session.getAttribute("role").equals("USER")) {
                ((HttpServletResponse) response).sendRedirect("/user");
            }
        } else {
            if (userService.validateUser(req.getParameter("login"), req.getParameter("password"))) {
                user = userService.getUserByName(req.getParameter("login"));
                req.getSession().setAttribute("login", user.getName());
                req.getSession().setAttribute("password", user.getPassword());
                req.getSession().setAttribute("role", user.getRole());
                if (user.getRole().equals("ADMIN")) {
                    ((HttpServletResponse) response).sendRedirect("/admin");
                } else if (user.getRole().equals("USER")) {
                    ((HttpServletResponse) response).sendRedirect("/user");
                }
            } else {
                req.setAttribute("message", "Write data again");
                request.getRequestDispatcher("/login").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
