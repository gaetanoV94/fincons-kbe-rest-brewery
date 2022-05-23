package guru.springframework.sfgrestbrewery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrestbrewery.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username")String username);
	
	Boolean existsByUsername(String username);
	
	@Query(value = "SELECT u.id, u.password, u.username, r.id, r.name FROM users u inner join user_roles ur on u.id = ur.user_id inner join roles r on r.id = ur.role_id where r.name = :role", nativeQuery = true)
	Iterable<User> findByRole(@Param("role") String role);

}
