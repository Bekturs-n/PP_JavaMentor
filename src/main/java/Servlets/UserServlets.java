package Servlets;

import Model.User;
import Service.UserServiceHibernate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@WebServlet("/users")
public class UserServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceHibernate userServiceHibernate =  UserServiceHibernate.getInstance();
        req.setAttribute("users", userServiceHibernate);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/users.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        UserServiceHibernate userServiceHibernate = UserServiceHibernate.getInstance();
        if (creatUser(req) != null) {
            user = creatUser(req);
            userServiceHibernate.addUsers(user);
        }
        req.setAttribute("users", userServiceHibernate);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/users.jsp");
        dispatcher.forward(req, resp);
    }

    protected User creatUser(HttpServletRequest req) {
        User user = null;
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String stringAge = req.getParameter("age");
        if (!name.isEmpty() && !surname.isEmpty() && !stringAge.isEmpty()) {
            user = new User(name, surname, Integer.parseInt(stringAge));
        }
        return user;
    }
}
