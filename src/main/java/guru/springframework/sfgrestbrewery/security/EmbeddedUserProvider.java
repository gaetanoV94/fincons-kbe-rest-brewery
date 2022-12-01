package guru.springframework.sfgrestbrewery.security;

import java.util.Collections;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Profile("embedded")
public class EmbeddedUserProvider extends FinconsAuthProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (!isValidUser(username, password)){
            throw new BadCredentialsException("You have provided bad credentials!!!");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password, Collections.emptyList());
        System.out.println("Your token is: "+token.getName());
        return token;
    }
    @Override
    public boolean isValidUser(String username, String password){
        return username != null
                && password != null
                && username.equals("my-user")
                && password.equals("my-password");
    }
}
