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

@WebServlet("/admin/edit")
public class EditServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        User user = userService.getUserByID(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/edit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String id = req.getParameter("id");
        User user = new User(req.getParameter("name"), req.getParameter("surname"), Integer.parseInt(req.getParameter("age")));
        userService.changeUserData(Long.parseLong(id), user);
        req.setAttribute("users", userService);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/admin.jsp");
        dispatcher.forward(req, resp);
    }
}
