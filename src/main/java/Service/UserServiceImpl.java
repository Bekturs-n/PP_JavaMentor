package Service;

import DAO.UserDAO;
import DAO.UserDaoFactory;
import DAO.UserHibernateDAO;
import Model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;
    private UserDAO userDAO;

    private UserServiceImpl() {
        userDAO = UserDaoFactory.getDAO();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void addUsers(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUsers();
    }

    @Override
    public String getNameById(Long id) {
        return userDAO.getUserById(id).getName();
    }

    @Override
    public User getUserByID(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void changeUserData(Long id, User user) {
        userDAO.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public boolean validateUser(String login, String password) {
        return userDAO.getAllUsers().stream()
                .anyMatch(f -> f.getName().equals(login) && f.getPassword().equals(password));
    }

    @Override
    public boolean nameIsEmpty(String login) {
        return userDAO.getAllUsers().stream()
                .filter(f -> f.getName().equals(login))
                .count() == 0;
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getAllUsers().stream()
                .filter(f -> f.getName().equals(name))
                .findFirst().orElse(null);
    }
}
