package DAO;

import Model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO() {
        connection = DBHelper.getInstance().getConnection();
    }

    @Override
    public void addUser(User user) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO user (name, surname, age) VALUES ('" + user.getName() + "', '" + user.getPassword() + "', '" + user.getAge() + "')");
        } catch (SQLException e) {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeQuery("select * from user ORDER by name");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                Long id = result.getLong(1);
                Integer age = result.getInt(2);
                String name = result.getString(3);
                String surname = result.getString(4);
                User user = new User(id, name, surname, age);
                list.add(user);
            }
        } catch (SQLException e) {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public User getUserById(Long id) {
        String answer = "";
        User user = null;
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from user where id = '" + id + "'");
            ResultSet result = stmt.getResultSet();
            result.next();
            user = new User(result.getString(2), result.getString(3), result.getInt(1));
//            answer = result.getString(2);
            result.close();
            stmt.close();
        } catch (SQLException e) {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public void updateUser(Long id, User user) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("update user set name = '" + user.getName() + "', surname = '" + user.getPassword() + "', age = '" + user.getAge() + "' where id = '" + id + "'");
            stmt.close();
        } catch (SQLException e) {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("delete from user where id = '" + id + "'");
            stmt.close();
        } catch (SQLException e) {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }
}
