package lt.company.bankserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lt.company.bankserver.exceptions.UsernameAlreadyExistException;
import lt.company.bankserver.model.User;
import lt.company.bankserver.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setUsername(newUser.getUsername());
			newUser.setConfirmPassword("");
			return userRepository.save(newUser);
		} catch (Exception e) {
			throw new UsernameAlreadyExistException("Username '" + newUser.getUsername() + "' already exist");
		}
		
	}
	
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
