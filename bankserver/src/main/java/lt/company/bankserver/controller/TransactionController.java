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

import lt.company.bankserver.model.Transaction;
import lt.company.bankserver.service.MapValidationErrorService;
import lt.company.bankserver.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/{number}")
	public List<Transaction> getTransactionByNumber(Principal principal, @PathVariable("number") String number) {
		return transactionService.getAllTransactionsByAccountNumber(number, principal.getName());		
	}
	
	@PostMapping("/{number}")
	public ResponseEntity<?> addTransactionToAccount(@Valid @RequestBody Transaction transaction, @PathVariable String number, BindingResult result, Principal principal) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		System.out.println("Add transaction to: " + principal.getName());
		Transaction newTransaction = transactionService.addTransactionToAccount(transaction, number, principal.getName());
		return new ResponseEntity<Transaction>(newTransaction, HttpStatus.CREATED);
		
	}

}
