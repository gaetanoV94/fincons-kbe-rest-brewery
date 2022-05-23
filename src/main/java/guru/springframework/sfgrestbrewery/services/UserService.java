package guru.springframework.sfgrestbrewery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import guru.springframework.sfgrestbrewery.domain.User;
import guru.springframework.sfgrestbrewery.repositories.UserRepository;
import guru.springframework.sfgrestbrewery.security.CustomUserDetails;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void saveNewUser(final User userDto) {
		
		User user = new User();
		
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(userDto.getRole());
		userRepository.save(user);
	}
	
	public void deleteUserById(Integer roleId) {
		userRepository.deleteById(roleId);
	}
	
	public Optional<User> findUserById(Integer userId) {
		return userRepository.findById(userId);
	}
	
	public User updateUserCredentials(Integer userId, User userToUpdate) {
		User user = findUserById(userId).get();
		
		if(user != null) {
			if(userToUpdate.getUsername() != null) {
				user.setUsername(userToUpdate.getUsername());
			}
			if(userToUpdate.getPassword() != null) {
				user.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
			}
				
		}
		userRepository.save(user);
		return user;
	}
	
	public List<User> getAllUsersByRoleName(String roleName){
		return userRepository.getAllUsersByRoleName(roleName);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
}
