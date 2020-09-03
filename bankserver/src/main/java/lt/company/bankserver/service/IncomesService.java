package lt.company.bankserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.Income;
import lt.company.bankserver.repositories.AccountRepository;
import lt.company.bankserver.repositories.IncomeRepository;

@Service
public class IncomesService {
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Income> getAllIncomeByAccountNumber(String number, String username) {
		
		List<Income> result = new ArrayList<>();
		Account account = accountRepository.getByNumber(number);
		result = account.getIncomes();		
		return result;
	}
	
	public Income addIncomeToAccount(Income income, String number, String username) {
		
		Account account = accountRepository.getByNumber(number);
		account.addIncome(income);
		account.setBalance(account.getBalance() + income.getIncomeAmount());
		
		return incomeRepository.save(income);
	}

}
