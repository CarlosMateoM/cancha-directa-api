package cancha.directa.service;

import cancha.directa.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findById (Long id);

    List<User> findAll();

    void save (User user);

    void updateById();

    void deleteById(Long id);
}
