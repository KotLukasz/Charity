package pl.coderslab.charity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.entity.Role;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void givenRole_whenFindByRole_thenRoleProvided () {
		//given
		String roleName = "ROLE_USER";
		//when
		Role role = roleRepository.findByRole(roleName);
		//then
		assertThat(role.getRole(), is(roleName));
	}

	@Test
	public void givenNotExistingRole_whenFindByRole_thenRoleIsNull () {
		//given
		String roleName = "ROLE_USER_ONE";
		//when
		Role role = roleRepository.findByRole(roleName);
		//then
		assertNull(role);
	}


}