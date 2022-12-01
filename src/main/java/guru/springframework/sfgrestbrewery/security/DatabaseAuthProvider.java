package guru.springframework.sfgrestbrewery.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrestbrewery.services.UserService;

@Component
@Profile("database")
public class DatabaseAuthProvider extends FinconsAuthProvider{
    private final PasswordEncoder passwordEncoder;
    private final UserService service;

    public DatabaseAuthProvider(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails databaseUser = null;
        if (!isValidUser(databaseUser, password)) {
            throw new BadCredentialsException("Specified credentials do not exist!!!");
        }
        return new UsernamePasswordAuthenticationToken(username, password, databaseUser.getAuthorities());
    }

    @Override
    public boolean isValidUser(String username, String password) {
        return false;
    }

    private boolean isValidUser(UserDetails user, String password) {
        return true;
    }
}
