package guru.springframework.sfgrestbrewery.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public abstract class CustomAuthProvider implements CustomAuthProviderInterface{
	
	public abstract Authentication authenticate(Authentication auth);
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
