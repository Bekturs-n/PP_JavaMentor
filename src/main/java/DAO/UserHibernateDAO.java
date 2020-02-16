package DAO;

import Model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public void updateUser(Long id, User user) {
        Transaction transaction = session.beginTransaction();
        User user1 = session.load(User.class, id);
        user1.setSuname(user.getSuname());
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        session.update(user1);
        transaction.commit();
        session.close();
    }

    public User getUserById(Long id) {
        Transaction transaction = session.beginTransaction();
        return session.load(User.class, id);
    }

    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("from User", User.class).getResultList();
        transaction.commit();
        return list;
    }

    public void deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
