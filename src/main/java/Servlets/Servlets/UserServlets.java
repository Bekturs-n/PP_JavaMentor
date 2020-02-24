package Servlets.Servlets;

import Model.User;
import Service.UserService;
import Service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        HttpSession session = req.getSession();
        User user = userService.getUserByName((String) session.getAttribute("login"));
        req.setAttribute("userName", user.getName());
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }
}
