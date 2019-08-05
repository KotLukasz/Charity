package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;


import java.util.HashSet;
import java.util.Set;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userService.findByEmail(email);
		if (user == null) {
			//TODO logging exceptions
			throw new UsernameNotFoundException("User already exists!" + email);
		} else if (user.getEnabled() == 0) {
			//TODO logging exceptions
			throw new UsernameNotFoundException("Account is blocked!" + email);
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		user.getRoles().forEach(r ->
				grantedAuthorities.add(new SimpleGrantedAuthority(r.getRole())));
		return new CurrentUser(user.getEmail(), user.getPassword(),
				grantedAuthorities, user);
	}
}
