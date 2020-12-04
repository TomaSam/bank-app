package lt.company.bankserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<Transaction> getTransactionsByType(String username, String number, String type) {
		List<Transaction> listType = new ArrayList<>();
		Account account = accountRepository.getByNumber(number);
		listType = account.getTransactions().stream().filter(transaction -> type.equals(transaction.getType().name()))
				.collect(Collectors.toList());
		return listType;
	}
	
	public List<Transaction> getTransactionsByCategory(String username, String number, String category) {
		List<Transaction> listCategory = new ArrayList<>();
		Account account = accountRepository.getByNumber(number);
		listCategory = account.getTransactions().stream().filter(transaction -> category.equals(transaction.getCategory().name()))
				.collect(Collectors.toList());		
		return listCategory;
	}
	
//public Transaction addTransactionToAccount(Transaction transaction, String number, String username) {
//			
//			Account account = accountRepository.getByNumber(number);
//			account.addTransaction(transaction);
//			if (transaction.getType().equals(TransactionType.DEBIT)) {
//				account.setBalance(account.getBalance() - transaction.getAmount());
//			}
//			else {
//				account.setBalance(account.getBalance() + transaction.getAmount());
//			}
//			
//			return transactionRepository.save(transaction);	
//	}
	
	public void transactionBetweenAccounts(Transaction senderTransaction, String senderNumber, String recipientNumber, String username) {
		
		Account senderAccount = accountRepository.getByNumber(senderNumber);
		Account recipientAccount = accountRepository.getByNumber(recipientNumber);
		// Add transaction to sender account and change his account balance.
		senderAccount.addTransaction(senderTransaction);
		senderAccount.setBalance(senderAccount.getBalance() - senderTransaction.getAmount());
		
		// Change and add transaction to recipient account and update his account balance.
		Transaction recipientTransaction = new Transaction(TransactionType.CREDIT, senderTransaction.getAmount(), senderTransaction.getDescription(), senderTransaction.getCategory());
		recipientAccount.addTransaction(recipientTransaction);
		recipientAccount.setBalance(recipientAccount.getBalance() + recipientTransaction.getAmount());
		
		transactionRepository.save(senderTransaction);
		transactionRepository.save(recipientTransaction);
	}
}
