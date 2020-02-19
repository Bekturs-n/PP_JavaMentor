package DAO;

import Model.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public void updateUser(Long id, User user);

    public void deleteUser(Long id);
}
