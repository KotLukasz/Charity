package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.User;

public interface UserService {

	User findByEmail(String email);

	void saveUser(User user);

}
