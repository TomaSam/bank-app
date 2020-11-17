package lt.company.bankserver.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.User;
import lt.company.bankserver.service.AccountService;
import lt.company.bankserver.service.MapValidationErrorService;
import lt.company.bankserver.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping
	public ResponseEntity<?> createNewAccount(Principal principal) {
		Account account1 = accountService.saveOrUpdateAccount(principal.getName());
		return new ResponseEntity<Account>(account1, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<Account>> getAllUserAccounts(Principal principal) {
		User user = userService.getByUsername(principal.getName());
		Iterable<Account> listOfAccounts = accountService.getAllAccounts(user);
		return new ResponseEntity<Iterable<Account>>(listOfAccounts, HttpStatus.OK);
	}
	
	@GetMapping("/{number}")
	public ResponseEntity<?> getAccountByNumber(@Valid @PathVariable String number, Principal principal) {
		Account account = accountService.getAccountByNumber(number, principal.getName());
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

}
