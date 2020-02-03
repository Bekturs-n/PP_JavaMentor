package Servlets;

import Model.User;
import Service.UserService;
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
        String message = "";
        resp.getWriter().println(req.getParameter("method"));
        String trUsers = "All Users";
        String trAddUp = "Add new Users";
        UserService userService = UserService.getInstance();
        System.out.println("Here");
        if (req.getParameter("method") == null) {
            if (userService.getAllUsers() != null) {
                message = formatUsers(userService);
            } else {
                message = "<h2> No users in DataBase";
            }
        } else if (req.getParameter("method").equals("delete")) {
            System.out.println("True");
            doDelete(req, resp);
        } else if (req.getParameter("method").equals("change")) {
            System.out.println("True");
            message = htmlCodeForChange(req, userService);
            trUsers = "Change User Data";
        } else if (req.getParameter("method").equals("put")){
            doPut(req, resp);
        }
        req.setAttribute("firstColumn", "<h2>" + trUsers + "</h2>");
        req.setAttribute("secondColumn", "<h2>" + trAddUp + "</h2>");
        req.setAttribute("message", "<h4>" + message + "</h4>");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("users.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(req.getParameter("method"));
        UserService userService = UserService.getInstance();
        String id = req.getParameter("id");
        if (changeParameter(req)) {
            User user = new User(req.getParameter("name"), req.getParameter("surname"), Integer.parseInt(req.getParameter("age")));
            userService.changeData(Long.parseLong(id), user);
            req.setAttribute("secondColumn", "");
            req.setAttribute("info", "Success! <br> <h3><a href = \"/users\"> Back </a></h3>");
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } else {
            req.setAttribute("secondColumn", "Error! <br> <h3><a href = \"/users\"> Back </a></h3>");
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        }
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UserService userService = UserService.getInstance();
        String nameOfDelUser = userService.getNameById(Long.parseLong(id)).getName();
        userService.deleteUsers(Long.parseLong(id));
        req.setAttribute("info", "<h2>" + nameOfDelUser + "\n" + " successfully deleted! </h2><br> <h3><a href = \"/users\"> Back </a></h3>");
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(req.getParameter("method"));
        User user = null;
        UserService userService = UserService.getInstance();
        if (creatUser(req) != null) {
            user = creatUser(req);
            userService.addUser(user);
            req.setAttribute("info", "");
            req.setAttribute("secondColumn", "New User was add! <br> <h3><a href = \"/users\"> Back </a></h3>");
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } else {
            req.setAttribute("info", "");
            req.setAttribute("secondColumn", "New User can't be added! <br> <h3><a href = \"/users\"> Back </a></h3>");
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        }
    }

    private String formatUsers(UserService userService) {
        String userDelete1 = "<table  width=\"80%\"> <tr> <td width = 50px><form method=\"GET\" action=\"/users\"><input type = \"hidden\" name=\"id\" value = \"";
        String userDelete2 = "\"> <input type = \"hidden\" name=\"method\" value = \"delete\"><button type=\"submit\">Delete</button></form></td>";
        String userChange1 = "<td><form method=\"GET\" action=\"/users\"><input type = \"hidden\" name=\"id\" value = \"";
        String userChange2 = "\"> <input type = \"hidden\" name=\"method\" value = \"change\"><button type=\"submit\">Change</button></form></td></tr></table>";

        AtomicLong i = new AtomicLong(0);
        return userService.getAllUsers()
                .stream()
                .map(f -> {
                    String user = i.addAndGet(1) + ". " + f.getName() + " " + f.getSuname() + " " + f.getAge() + userDelete1 + f.getId() + userDelete2
                            + userChange1 + f.getId() + userChange2;
//                        String allUsers = String.join("  ", user);
                    return user;
                })
                .collect(Collectors.joining());
    }

    protected String htmlCodeForChange(HttpServletRequest req, UserService userService) {
        User user = userService.getNameById(Long.parseLong(req.getParameter("id")));

        return new String("You will change " + user.getName() + "<br> <br><form method=\"post\" action=\"/users\">\n" +
                "            <input type = \"hidden\" name=\"id\" value = \"" + req.getParameter("id") + "\"><br>\n" +
                "            New name<br>    <input name=\"name\" value = \"" + user.getName() + "\"><br>\n" +
                "            New surname<br> <input name=\"surname\" value = \"" + user.getSuname() + "\"><br>\n" +
                "            New age<br>     <input name=\"age\" type=\"number\" value =\""+user.getAge()+"\"><br><br>\n" +
                "            <button type=\"submit\"> ChangeUser</button>\n" +
                "            </form>");
    }

    private boolean changeParameter(HttpServletRequest req) {
        if (req.getParameter("name").isEmpty() && req.getParameter("surname").isEmpty() && req.getParameter("age").isEmpty()) {
            return false;
        }
        return true;
    }
}
