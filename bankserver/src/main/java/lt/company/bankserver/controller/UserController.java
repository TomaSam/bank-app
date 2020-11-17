package lt.company.bankserver.controller;

import javax.security.auth.login.FailedLoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lt.company.bankserver.model.User;
import lt.company.bankserver.payload.JWTLoginSuccessResponse;
import lt.company.bankserver.payload.LoginRequest;
import lt.company.bankserver.payload.MessageResponse;
import lt.company.bankserver.repositories.UserRepository;
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
	
//	@Autowired
//	private MapValidationErrorService mapValidationErrorService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private UserValidator userValidator;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws FailedLoginException {
		
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
			
			return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));	
		}
		catch (Exception e) {
			throw new FailedLoginException("Username or password is wrong! Try again.");
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		
		if (userRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username already exist!"));
		}
		
		User newUser = userService.saveUser(user);
		
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
		
	}

}
