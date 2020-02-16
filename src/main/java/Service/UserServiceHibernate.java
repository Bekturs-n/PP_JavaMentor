package Service;

import DAO.UserHibernateDAO;
import Model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class UserServiceHibernate {

    private static UserServiceHibernate userServiceHibernate;

    private SessionFactory sessionFactory;

    private UserServiceHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserServiceHibernate getInstance(){
        if(userServiceHibernate == null){
            userServiceHibernate = new UserServiceHibernate(DBHelper.getSessionFactory());
        }
        return userServiceHibernate;
    }

    public boolean addUsers(User user){
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        userHibernateDAO.addUser(user);
        return true;
    }

    public List<User> getAllUser(){
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    public String getNameById(Long id){
        return new UserHibernateDAO(sessionFactory.openSession()).getUserById(id).getName();
    }

    public User getUserByID(Long id){
        return new UserHibernateDAO(sessionFactory.openSession()).getUserById(id);
    }

    public void changeUserData(Long id, User user){
        new UserHibernateDAO(sessionFactory.openSession()).updateUser(id, user);
    }

    public void deleteUser(Long id){
        new UserHibernateDAO(sessionFactory.openSession()).deleteUser(id);
    }

}
