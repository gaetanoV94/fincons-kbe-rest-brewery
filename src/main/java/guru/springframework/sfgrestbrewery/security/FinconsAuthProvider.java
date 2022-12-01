package guru.springframework.sfgrestbrewery.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

public abstract class FinconsAuthProvider implements AuthenticationProvider {

    public abstract Authentication authenticate(Authentication authentication) throws AuthenticationException ;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public abstract boolean isValidUser(String username, String password);
}
