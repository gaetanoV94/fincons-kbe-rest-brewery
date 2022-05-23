package guru.springframework.sfgrestbrewery.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfgrestbrewery.domain.User;
import guru.springframework.sfgrestbrewery.exception.UserNotFoundException;
import guru.springframework.sfgrestbrewery.services.UserService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/v1/")
@RestController
@ResponseBody
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "users")
	public ResponseEntity<List<User>> getUsers(){
		log.info("Getting all users from the Database");
		final Iterable<User> userIterable = userService.getAllUsers();
		final List<User> users = StreamSupport
				.stream(userIterable.spliterator(), false)
				.collect(Collectors.toList());
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping(value = "save-new-user")
	public ResponseEntity<Void> saveNewUser(@RequestBody User user){
		log.info("Saving new user in the Database");
		userService.saveNewUser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "delete-user/{userId}")
	public ResponseEntity<Void> deleteUserById(@PathVariable(name = "userId") Integer userId){
		log.info("Deleting user with userId {} from the Database", userId);
		userService.deleteUserById(userId);
		log.info("Record with userId {} has been deleted", userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value = "get-user-from-id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") final Integer userId){
		log.info("Getting user with userId {} from the Database.", userId);
		final User user = userService.findUserById(userId)
				.orElseThrow(() -> new UserNotFoundException
					("User with userId " + userId 
							+ " not found in the Database"));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-all-users-by-rolename/{rolename}")
	public ResponseEntity<List<User>> getAllUsersByRoleName(@PathVariable(name = "rolename") String roleName){
		log.info("Getting all users with rolename {} from the Database.", roleName);
		List<User> users = userService.getAllUsersByRoleName(roleName);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping(value = "update-user-credentials/{userId}")
	public ResponseEntity<Void> updateUserCredentials(
			@PathVariable(name = "userId") final Integer userId,
			@RequestBody User user){
		log.info("Saving new beer in the Database");
		userService.updateUserCredentials(userId, user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
