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

@WebServlet("/edit")
public class EditServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceHibernate userService = UserServiceHibernate.getInstance();
        User user = userService.getUserByID(Long.parseLong(req.getParameter("id")));
        req.setAttribute("users", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/edit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceHibernate userService = UserServiceHibernate.getInstance();
        String id = req.getParameter("id");
//        if (checkParameter(req)) {
        User user = new User(req.getParameter("name"), req.getParameter("surname"), Integer.parseInt(req.getParameter("age")));
        userService.changeUserData(Long.parseLong(id), user);
//        }
        UserServiceHibernate userServiceHibernate =  UserServiceHibernate.getInstance();
        req.setAttribute("users", userServiceHibernate);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/users.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean checkParameter(HttpServletRequest req) {
        if (req.getParameter("name").isEmpty() && req.getParameter("surname").isEmpty() && req.getParameter("age").isEmpty()) {
            return false;
        }
        return true;
    }
}
