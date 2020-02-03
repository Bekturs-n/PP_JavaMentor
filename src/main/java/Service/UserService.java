package Service;

import DAO.UserDAO;
import Model.User;

import java.sql.*;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserService() {
    }

    public void changeData(Long id, User user){
        getUserDao().ubdateUser(id, user);
    }

    public static UserService getInstance(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public User getNameById(Long id){
        return getUserDao().getUserByidUser(id);
    }

    public void deleteUsers(Long id){
        getUserDao().deleteUser(id);
    }

    public List<User> getAllUsers(){
        return getUserDao().getUsers();
    }

    public void addUser(User user){
        getUserDao().addUser(user);
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("exam?").          //db name
                    append("user=root&").           //login
                    append("password=root").        //password
                    append("&serverTimezone=Europe/Moscow").
                    append("&useSSL=false");
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDao() {
        return new UserDAO(getMysqlConnection());
    }
}
