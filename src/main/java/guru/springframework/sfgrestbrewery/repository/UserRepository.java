package guru.springframework.sfgrestbrewery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrestbrewery.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.username = :username")
	User getUserByUsername(@Param("username")String username);
	
	Boolean existsByUsername(String username);
	
	@Query(value = "SELECT u.id, u.password, u.username, r.id, r.name "
			+ "FROM users u "
			+ "INNER JOIN user_roles ur "
			+ "ON u.id = ur.user_id "
			+ "INNER JOIN roles r "
			+ "ON r.id = ur.role_id "
			+ "WHERE r.name = :role", 
			nativeQuery = true)
	Iterable<User> getUserByRole(@Param("role") String role);

}
