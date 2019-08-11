package pl.coderslab.charity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	List<User> findUsersByRoles(Role role);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update User u set u.firstName = :firstName, u.lastName =:lastName, u.email = :email, u.password =:password where u.id = :id")
	void updateUserSetFirstNameAndLastNameAndEmailAndPassword(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName,
	@Param("email") String email, @Param("password") String password);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update User u set u.enabled =:enabled where u.id = :id")
	void updateUserSetEnabled(@Param("id") Long id, @Param("enabled") int enabled);

}

