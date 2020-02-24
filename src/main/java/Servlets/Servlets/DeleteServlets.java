package Servlets.Servlets;

import Service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String id = req.getParameter("id");
        userService.deleteUser(Long.parseLong(id));
        req.setAttribute("users", userService);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin");
        requestDispatcher.forward(req, resp);
    }
}
