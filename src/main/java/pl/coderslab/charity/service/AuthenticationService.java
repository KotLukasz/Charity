package pl.coderslab.charity.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	public boolean givenEmailExistInUserDatabase(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

	public User authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		boolean equalPassword = BCrypt.checkpw(password, user.getPassword());
		if (equalPassword) {
			return user;
		}
		return null;
	}

}
