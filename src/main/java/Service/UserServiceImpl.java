package Service;

import DAO.UserHibernateDAO;
import Model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    @Override
    public boolean addUsers(User user) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO();
        userHibernateDAO.addUser(user);
        return true;
    }

    @Override
    public List<User> getAllUser() {
        return new UserHibernateDAO().getAllUsers();
    }

    @Override
    public String getNameById(Long id) {
        return new UserHibernateDAO().getUserById(id).getName();
    }

    @Override
    public User getUserByID(Long id) {
        return new UserHibernateDAO().getUserById(id);
    }

    @Override
    public void changeUserData(Long id, User user) {
        new UserHibernateDAO().updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        new UserHibernateDAO().deleteUser(id);
    }
}
