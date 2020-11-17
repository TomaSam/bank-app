package lt.company.bankserver.model;

import java.security.SecureRandom;

public class AccountNumberGenerator {
	
	public static String generateAccountNumber() {
		
		String NUMBER = "0123456789";
		String fixedPart = "BNS3000";
		
		StringBuilder sb = new StringBuilder(15);
		
		sb.append(fixedPart);
		
		SecureRandom random = new SecureRandom();
		
		for (int i=0; i<8; i++) {
			int rndCharAt = random.nextInt(NUMBER.length());
			char rndChar = NUMBER.charAt(rndCharAt);
			sb.append(rndChar);
		}
		
		return sb.toString();
		
	}	
}
