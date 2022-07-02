package guru.springframework.sfgrestbrewery.loader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrestbrewery.model.User;
import guru.springframework.sfgrestbrewery.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import guru.springframework.sfgrestbrewery.model.ERole;
import guru.springframework.sfgrestbrewery.model.Role;

@Slf4j
@Component
public class UserLoader implements CommandLineRunner{

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	public UserLoader(UserRepository userRepo, PasswordEncoder encoder) {
		this.userRepository = userRepo;
		this.passwordEncoder = encoder;
	}

	@Override
	public void run(String... args) throws Exception {
		loadUserObjects();	
	}
	
	private synchronized void loadUserObjects() {
		log.debug("Loading initial data. Count is: {}", userRepository.count() );

		if(userRepository.count() == 0) {

			userRepository.save(User.builder()
					.username("gaetano")
					.password(passwordEncoder.encode("admin1234"))
					.roles(initRoles(Arrays.asList(ERole.ADMIN)))
					.build());
			
			userRepository.save(User.builder()
					.username("domenico")
					.password(passwordEncoder.encode("user1234"))
					.roles(initRoles(Arrays.asList(ERole.USER)))
					.build());

			log.debug("Users Records loaded: {}", userRepository.count());
		}
	}
	
	private List<Role> initRoles(List<ERole> roles){
		List<Role> generatedRoles = new ArrayList<Role>();
		roles.forEach(r ->generatedRoles.add(Role.builder().name(r).build()));
		return generatedRoles;
	}

}