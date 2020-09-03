package lt.company.bankserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.company.bankserver.exceptions.AccountNotFoundException;
import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.Expenses;
import lt.company.bankserver.repositories.AccountRepository;
import lt.company.bankserver.repositories.ExpensesRepository;

@Service
public class ExpensesService {
	
	@Autowired
	private ExpensesRepository expensesRepository;
		
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Expenses> getAllExpensesByAccountNumber(String number, String username) {
		
		List<Expenses> result = new ArrayList<>();
		Account account = accountRepository.getByNumber(number);
		result = account.getExpenses();
			
		return result;
	}
	
	public Expenses addExpensesToAccount(Expenses expense, String number, String username) {
		
		if (expense.getId() != null) {
			Expenses existingExpenses = expensesRepository.findById(expense.getId()).get();
			
			if (existingExpenses != null && (!existingExpenses.getAccount().getNumber().equals(number))) {
				throw new AccountNotFoundException("Account not found in your profile");
			}
		}
			
			Account account = accountRepository.getByNumber(number);
			account.addExpenses(expense);
			account.setBalance(account.getBalance() - expense.getExpensesAmount());
			
			return expensesRepository.save(expense);	
	}
	
	
	
	

}
