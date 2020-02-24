package Servlets.FilterServlet;

import Model.User;
import Service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter",
        urlPatterns = {"/admin/*"})
public class FilterAdmin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
            String role = (String) session.getAttribute("role");
            if (role.equals("ADMIN")) {
                chain.doFilter(request, response);
            } else if (role.equals("USER")) {
                ((HttpServletResponse) response).sendRedirect("/user");
            } else {
                req.setAttribute("message", "Write data again");
                request.getRequestDispatcher("/login").forward(request, response);
            }
        } else {
            ((HttpServletResponse) response).sendRedirect("/login");
        }
    }

    private void openPage(HttpServletResponse resp, HttpServletRequest req, String role) throws ServletException, IOException {
        switch (role) {
            case "ADMIN":
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            case "USER":
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            default:
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
