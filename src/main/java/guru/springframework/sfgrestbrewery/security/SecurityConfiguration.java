package guru.springframework.sfgrestbrewery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private FinconsAuthProvider authProvider;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception{
      auth.authenticationProvider(authProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
      http.authorizeRequests()
              .anyRequest()
              .authenticated()
              .and()
              .httpBasic()
              .and()
              .formLogin()
              .and()
              .logout();
  }


}
