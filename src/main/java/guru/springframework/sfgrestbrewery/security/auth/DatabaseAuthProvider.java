package guru.springframework.sfgrestbrewery.security.auth;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrestbrewery.services.UserService;

@Component
@ConditionalOnProperty(
	    name="security.auth.type", 
	    havingValue = "database", 
	    matchIfMissing = true)
public class DatabaseAuthProvider extends CustomAuthProvider{
	
	private final UserService service;
	
	private final PasswordEncoder passwordEncoder;
	
	public DatabaseAuthProvider(UserService service, PasswordEncoder passwordEncoder) {
		this.service = service;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	@Transactional
	public Authentication authenticate(Authentication auth) {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		UserDetails databaseUser = service.loadUserByUsername(username);
		if (!isValidUser(databaseUser, password)) {
			throw new BadCredentialsException("Specified credentials do not exist!!!");
		}
		return new UsernamePasswordAuthenticationToken(username, password, databaseUser.getAuthorities());
	}
	
	
	private boolean isValidUser(UserDetails user, String password) {
		return user != null 
				&& password != null
				&& user.getPassword() != null
				&& passwordEncoder.matches(password, user.getPassword());
	}
}
