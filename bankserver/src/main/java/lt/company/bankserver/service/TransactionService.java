package lt.company.bankserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.company.bankserver.exceptions.AccountNotFoundException;
import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.Transaction;
import lt.company.bankserver.model.TransactionType;
import lt.company.bankserver.repositories.AccountRepository;
import lt.company.bankserver.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
public List<Transaction> getAllTransactionsByAccountNumber(String number, String username) {
		
		List<Transaction> result = new ArrayList<>();
		Account account = accountRepository.getByNumber(number);
		result = account.getTransactions();
			
		return result;
	}
	
	public Transaction addTransactionToAccount(Transaction transaction, String number, String username) {
		
		if (transaction.getId() != null) {
			Transaction existingTransaction = transactionRepository.findById(transaction.getId()).get();
			
			if (existingTransaction != null && (!existingTransaction.getAccount().getNumber().equals(number))) {
				throw new AccountNotFoundException("Account not found in profile");
			}
		}
			
			Account account = accountRepository.getByNumber(number);
			account.addTransaction(transaction);
			if (transaction.getType().equals(TransactionType.DEBIT)) {
				account.setBalance(account.getBalance() - transaction.getAmount());
			}
			else {
				account.setBalance(account.getBalance() + transaction.getAmount());
			}
			
			
			return transactionRepository.save(transaction);	
	}
}
