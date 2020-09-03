package lt.company.bankserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.company.bankserver.exceptions.AccountIdException;
import lt.company.bankserver.exceptions.AccountNotFoundException;
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
	
	public Account saveOrUpdateAccount(Account account, String username) {
		
		if (account.getId() != null) {
			Account existingAccount = accountRepository.getByNumber(account.getNumber());
			
			if (existingAccount != null && (!existingAccount.getUser().getUsername().equals(username))) {
				throw new AccountNotFoundException("Account not found in your profile");
			}
			else if (existingAccount == null) {
				throw new AccountNotFoundException("Account with " + account.getNumber() + 
						" cannot be updated because it does not exist");
			}
		}
		
		try {
			User user = userRepository.findByUsername(username);
			account.setUser(user);
			account.setNumber(account.getNumber());
			
			return accountRepository.save(account);
		}
		catch (Exception e) {
			throw new AccountIdException("Account number " + account.getNumber() + " already exist");
		}
		
	}

}
