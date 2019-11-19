package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.model.User;
import ua.nure.apz.makieiev.apz.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User add(User user) {
		return null;
	}

	@Override
	public Optional<User> getById(long id) {
		return Optional.empty();
	}

	@Override
	public Optional<User> getByLogin(String login) {
		return Optional.empty();
	}

	@Override
	public boolean checkPassword(User user, String password) {
		return false;
	}

}