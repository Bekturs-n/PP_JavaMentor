package Servlets.Servlets;

import Model.User;
import Service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        req.setAttribute("users", userService);
        req.setAttribute("info", "");
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/admin.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        if ((user = creatUser(req)) != null) {
            if (userService.nameIsEmpty(user.getName())) {
                userService.addUsers(user);
                req.setAttribute("info", "");
            } else {
                req.setAttribute("info", "Please choose another name");
            }
        }
        req.setAttribute("users", userService);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/admin.jsp");
        dispatcher.forward(req, resp);
    }

    protected User creatUser(HttpServletRequest req) {
        User user = null;
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String stringAge = req.getParameter("age");
        if (!name.isEmpty() && !surname.isEmpty() && !stringAge.isEmpty()) {
            user = new User(name, surname, Integer.parseInt(stringAge), req.getParameter("role"));
        }
        return user;
    }
}
