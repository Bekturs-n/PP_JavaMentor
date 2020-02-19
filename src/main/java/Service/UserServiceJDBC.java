package Service;

import DAO.UserJdbcDAO;
import Model.User;

import java.util.List;

public class UserServiceJDBC {

    private static UserServiceJDBC userServiceJDBC;

    public static UserServiceJDBC getInstance() {
        if (userServiceJDBC == null) {
            userServiceJDBC = new UserServiceJDBC();
        }
        return userServiceJDBC;
    }

    public void changeData(Long id, User user) {
        new UserJdbcDAO().updateUser(id, user);
    }

    public String getNameById(Long id) {
        return new UserJdbcDAO().getUserById(id).getName();
    }

    public void deleteUsers(Long id) {
        new UserJdbcDAO().deleteUser(id);
    }

    public List<User> getAllUsers() {
        return new UserJdbcDAO().getAllUsers();
    }

    public void addUser(User user) {
        new UserJdbcDAO().addUser(user);
    }

    //    private static UserJdbcDAO getUserDao() {
//        return new UserJdbcDAO(getMysqlConnection());
//    }

}
