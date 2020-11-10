package lt.company.bankserver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.company.bankserver.model.User;
import lt.company.bankserver.payload.JWTLoginSuccessResponse;
import lt.company.bankserver.payload.LoginRequest;
import lt.company.bankserver.security.JwtTokenProvider;
import lt.company.bankserver.service.MapValidationErrorService;
import lt.company.bankserver.service.UserService;
import lt.company.bankserver.validator.UserValidator;
import static lt.company.bankserver.security.SecurityConstants.TOKEN_PREFIX;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserValidator userValidator;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null) {
			return errorMap;
		}
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));	
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
		userValidator.validate(user, result);
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		
		User newUser = userService.saveUser(user);
		
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
		
	}

}
