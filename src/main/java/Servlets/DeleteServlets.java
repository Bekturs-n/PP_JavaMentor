package Servlets;

import Service.UserServiceHibernate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UserServiceHibernate userService = UserServiceHibernate.getInstance();
        String nameOfDelUser = userService.getNameById(Long.parseLong(id));
        userService.deleteUser(Long.parseLong(id));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/users");
        requestDispatcher.forward(req, resp);
    }
}
