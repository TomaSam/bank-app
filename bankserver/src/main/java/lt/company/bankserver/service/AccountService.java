package lt.company.bankserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.User;
import lt.company.bankserver.repositories.AccountRepository;
import lt.company.bankserver.repositories.UserRepository;

@Service
public class AccountService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Iterable<Account> getAllAccounts(User user) {
		return accountRepository.findByUser(user);	
	}
	
	public Account saveOrUpdateAccount(String username) {
		
			User user = userRepository.findByUsername(username);
			Account account = new Account();
			account.setUser(user);
			account.setNumber(account.getNumber());
			
			return accountRepository.save(account);
		
	}
	
	public Account getAccountByNumber(String number, String username) {
			return accountRepository.getByNumber(number);
	}

}
