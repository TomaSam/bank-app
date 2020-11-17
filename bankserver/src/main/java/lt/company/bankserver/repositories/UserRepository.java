package lt.company.bankserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.company.bankserver.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
	User getById(Long id);
	
	Boolean existsByUsername(String username);

}
