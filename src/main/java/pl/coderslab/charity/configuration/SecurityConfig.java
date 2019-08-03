package pl.coderslab.charity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.service.UrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UrlAuthenticationSuccessHandler authenticationSuccessHandler(){
		return new UrlAuthenticationSuccessHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/register").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").successHandler(authenticationSuccessHandler()).permitAll()
				.and()
				.logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/403");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
