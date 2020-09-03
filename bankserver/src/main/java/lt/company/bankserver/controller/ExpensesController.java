package lt.company.bankserver.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.company.bankserver.exceptions.AccountNotFoundException;
import lt.company.bankserver.model.Expenses;
import lt.company.bankserver.service.ExpensesService;
import lt.company.bankserver.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {
	
	@Autowired
	private ExpensesService expensesService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	@GetMapping("/{number}")
	public List<Expenses> getExpensesByNumber(Principal principal, @PathVariable("number") String number) {
		
		List<Expenses> result = expensesService.getAllExpensesByAccountNumber(number, principal.getName());
		if (result.size() == 0) {
			throw new AccountNotFoundException("Not found expenses");
		}	
		return result;	
	}
	
	@PostMapping("/{number}")
	public ResponseEntity<?> addExpenses(@Valid @RequestBody Expenses expense, @PathVariable String number, BindingResult result, Principal principal) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		System.out.println("Add expense method: " + principal.getName());
		Expenses expenseNew = expensesService.addExpensesToAccount(expense, number, principal.getName());
		return new ResponseEntity<Expenses>(expenseNew, HttpStatus.CREATED);
	}

}
