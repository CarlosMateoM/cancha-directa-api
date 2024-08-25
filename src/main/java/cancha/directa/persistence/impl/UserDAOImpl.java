package cancha.directa.persistence.impl;

import cancha.directa.model.User;
import cancha.directa.persistence.IUserDAO;
import cancha.directa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements IUserDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
