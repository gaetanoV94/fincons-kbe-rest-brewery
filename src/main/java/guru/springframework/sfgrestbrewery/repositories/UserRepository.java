package guru.springframework.sfgrestbrewery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrestbrewery.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	@Query("select u from User u where u.role = :role")
	List<User> getAllUsersByRoleName(@Param("role") String roleName);
	
	User findByUsername(String username);

}
