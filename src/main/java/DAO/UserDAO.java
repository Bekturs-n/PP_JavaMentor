package DAO;

import Model.User;

import java.util.List;

public interface UserDAO {
    public String getNameByidUser(Long id);

    public void ubdateUser(Long id, User user);

    public void deleteUser(Long id);

    public List<User> getUsers();

    public void addUser(User user);
}
