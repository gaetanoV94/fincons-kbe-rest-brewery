package guru.springframework.sfgrestbrewery.security.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

public interface CustomAuthProviderInterface extends AuthenticationProvider{
	public Authentication authenticate(Authentication auth);
	public boolean supports(Class<?> authentication);
}
