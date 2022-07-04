package guru.springframework.sfgrestbrewery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import guru.springframework.sfgrestbrewery.security.auth.CustomAuthProviderInterface;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthProviderInterface authProvider;
	
	/*@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider =
				new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}*/
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authProvider);
	}
		
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/api/v1/beers").hasAnyAuthority("ADMIN", "USER")
			.antMatchers("/api/v1/get-beer-from-id/{id}").hasRole("ADMIN")
			.antMatchers("/api/v1/get-beer-from-name/{beerName}").hasAnyAuthority("ADMIN", "USER")
			.antMatchers("/api/v1/get-beer-from-upc/{beerUpc}").hasRole("ADMIN")
			.antMatchers("/api/v1/get-beer-from-beer-style").hasAnyAuthority("ADMIN", "USER")
			.antMatchers("/api/v1/save-new-beer").hasRole("ADMIN")
			.antMatchers("/api/v1/delete-beer/{beerId}").hasRole("ADMIN")
			.antMatchers("/api/v1/users").hasAnyAuthority("ADMIN")
			.antMatchers("/api/v1/save-new-user").hasAnyAuthority("ADMIN")
			.antMatchers("/api/v1/delete-user/{userId}").hasAnyAuthority("ADMIN")
			.antMatchers("/api/v1/get-user-from-id/{id}").hasAnyAuthority("ADMIN")
			.antMatchers("/api/v1/get-all-users-by-rolename/{rolename}").hasAnyAuthority("ADMIN")
			.antMatchers("/api/v1/update-user-credentials/{userId}").hasAnyAuthority("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

}
