package guru.springframework.sfgrestbrewery.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrestbrewery.domain.User;
import guru.springframework.sfgrestbrewery.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

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
					.role("ADMIN")
					.build());
			
			userRepository.save(User.builder()
					.username("domenico")
					.password(passwordEncoder.encode("user1234"))
					.role("USER")
					.build());

			log.debug("Users Records loaded: {}", userRepository.count());
		}
	}

}
