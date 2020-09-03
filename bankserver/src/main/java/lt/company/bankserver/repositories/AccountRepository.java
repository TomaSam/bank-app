package lt.company.bankserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.User;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Account getByNumber(String number);

	Iterable<Account> findAll();
	
	Iterable<Account> findByUser(User user);
	
	

}
