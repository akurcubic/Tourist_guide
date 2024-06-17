package rs.raf.turisticki_vodic_be.repositories.user;

import rs.raf.turisticki_vodic_be.entities.User;

import java.util.List;

public interface UserRepository {

    User getByEmail(String email);
    List<User> getAllUsers();
    User create(User user);
    User changeUser(User user);
    User getById(int id);
    User getByName(String name);
    List<User> getAllUserByPage(int page);

}
