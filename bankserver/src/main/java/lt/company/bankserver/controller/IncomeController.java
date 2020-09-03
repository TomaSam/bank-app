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

import lt.company.bankserver.exceptions.TransactionNotFound;
import lt.company.bankserver.model.Income;
import lt.company.bankserver.service.IncomesService;
import lt.company.bankserver.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

	@Autowired
	private IncomesService incomeService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/{number}")
	public List<Income> getIncomesByNumber(@PathVariable("number") String number, Principal principal) {
		
		List<Income> result = incomeService.getAllIncomeByAccountNumber(number, principal.getName());
		if (result.size() == 0) {
			throw new TransactionNotFound("Not found incomes");
		}
		return result;	
	}
	
	@PostMapping("/{number}")
	public ResponseEntity<?> addIncome(@Valid @RequestBody Income income, @PathVariable String number, BindingResult result, Principal principal) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		
		System.out.println("Add income method: " + principal.getName());
		Income incomeNew = incomeService.addIncomeToAccount(income, number, principal.getName());
		return new ResponseEntity<Income>(incomeNew, HttpStatus.CREATED);
	}
	
	
}
