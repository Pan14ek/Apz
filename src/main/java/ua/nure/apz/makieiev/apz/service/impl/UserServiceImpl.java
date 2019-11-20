package ua.nure.apz.makieiev.apz.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.exception.NotUniqueUserException;
import ua.nure.apz.makieiev.apz.model.User;
import ua.nure.apz.makieiev.apz.repository.UserRepository;
import ua.nure.apz.makieiev.apz.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        try {
            String md5Password = DigestUtils.md5Hex(user.getPassword());
            user.setPassword(md5Password);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueUserException("The database contains a user with particular fields");
        }
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public boolean removeById(long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return Objects.equals(user.getPassword(), password);
    }

}