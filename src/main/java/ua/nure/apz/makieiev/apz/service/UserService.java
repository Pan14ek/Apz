package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.User;

import java.util.Optional;

public interface UserService {

	User add(User user);

	Optional<User> getById(long id);

	Optional<User> getByLogin(String login);

	boolean checkPassword(User user, String password);

}