package cancha.directa.service.impl;

import cancha.directa.model.User;
import cancha.directa.persistence.IUserDAO;
import cancha.directa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    @Autowired
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.userDAO.findAll();
    }

    @Override
    public void save(User user) {
        this.userDAO.save(user);
    }

    @Override
    public void updateById(){

    }

    @Override
    public void deleteById(Long id) {
        this.userDAO.deleteById(id);
    }
}
