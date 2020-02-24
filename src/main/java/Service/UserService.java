package Service;

import Model.User;

import java.util.List;

public interface UserService {

    public void addUsers(User user);

    public List<User> getAllUser();

    public String getNameById(Long id);

    public User getUserByID(Long id);

    public void changeUserData(Long id, User user);

    public void deleteUser(Long id);

    public boolean validateUser(String login, String password);

    public User getUserByName(String name);

    public boolean nameIsEmpty(String login);
}
