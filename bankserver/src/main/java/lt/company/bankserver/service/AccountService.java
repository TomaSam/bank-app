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
		
//		if (account.getId() != null) {
//			Account existingAccount = accountRepository.getByNumber(account.getNumber());
//			
//			if (existingAccount != null && (!existingAccount.getUser().getUsername().equals(username))) {
//				throw new AccountNotFoundException("Account not found in your profile");
//			}
//			else if (existingAccount == null) {
//				throw new AccountNotFoundException("Account with " + account.getNumber() + 
//						" cannot be updated because it does not exist");
//			}
//		}
//		
//		try {
			User user = userRepository.findByUsername(username);
			Account account = new Account();
			account.setUser(user);
			account.setNumber(account.getNumber());
			
			return accountRepository.save(account);
//		}
//		catch (Exception e) {
//			throw new AccountNotFoundException("Account number " + account.getNumber() + " already exist");
//		}
		
	}
	
	public Account getAccountByNumber(String number, String username) {
//		try {
			return accountRepository.getByNumber(number);
//		}
//		catch (Exception e) {
//			throw new AccountNotFoundException("Account with number " + number + " didn't find!"); 
//		}
		
	}

}
