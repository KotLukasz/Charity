package pl.coderslab.charity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void givenEmail_whenFindByEmail_thenUserProvided() {
		//given
		String email = "admin@admin";
		//when
		User user = userRepository.findByEmail(email);
		//then
		assertThat(user, notNullValue());
		assertThat(email, is(user.getEmail()));
	}

	@Test
	public void givenRoles_whenFindUsersByRoles_thenUsersWithGivenRole() {
		//given
		Role roleAdmin = roleRepository.findByRole("ROLE_ADMIN");
		Role roleUser = roleRepository.findByRole("ROLE_USER");
		//when
		List<User> usersAdmin = userRepository.findUsersByRoles(roleAdmin);
		List<User> usersUser = userRepository.findUsersByRoles(roleUser);
		//then
		assertThat(usersAdmin, notNullValue());
		assertThat(usersUser, hasSize(0));

	}

	@Test
	@Transactional
	public void givenFirstNameAndLastNameAndEmailAndPasswordAndEnabled_whenUpdateUser_thenUserIsUpdated() {
		//given
		User user = userRepository.findByEmail("admin@admin");
		//when
		userRepository.updateUserSetFirstNameAndLastNameAndEmailAndPassword(user.getId(), "Ola", "Xxx", "Ola@xxx", "123");
		String emailToCompare = userRepository.getOne(user.getId()).getEmail();
		//then
		assertThat(emailToCompare, is("Ola@xxx"));
	}

	@Test
	@Transactional
	public void givenEnabled_whenUpdateUser_thenEnabledUpdated () {
		//given
		int enabled = 0;
		//when
		User user = userRepository.findByEmail("admin@admin");
		userRepository.updateUserSetEnabled(user.getId(),0);
		//then
		assertThat(enabled, is(userRepository.findByEmail("admin@admin").getEnabled()));
	}
}