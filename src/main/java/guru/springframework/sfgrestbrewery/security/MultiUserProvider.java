package guru.springframework.sfgrestbrewery.security;

import java.util.Collections;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Profile("multiple")
public class MultiUserProvider extends FinconsAuthProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new UsernamePasswordAuthenticationToken("pippo","pippo", Collections.emptyList());
    }

    @Override
    public boolean isValidUser(String username, String password) {
        return false;
    }
}
