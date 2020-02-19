package DAO;

import Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO{
    private SessionFactory sessionFactory;

    public UserHibernateDAO(){
        sessionFactory = DBHelper.getSessionFactory()
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> list = session.createQuery("from User", User.class).getResultList();
        session.close();
        return list;
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.load(User.class, id);
        session.close();
        return user;
    }

    @Override
    public void updateUser(Long id, User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user1 = session.load(User.class, id);
            user1.setSuname(user.getSuname());
            user1.setAge(user.getAge());
            user1.setName(user.getName());
            session.update(user1);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
}

//    public UserHibernateDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }