package lt.company.bankserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.Transaction;
import lt.company.bankserver.model.TransactionCategory;
import lt.company.bankserver.model.TransactionType;
import lt.company.bankserver.service.AccountService;
import lt.company.bankserver.service.TransactionService;

//@WithMockUser(username = "usertest", password = "password")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ServiceTest {
	
	@Autowired
    private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;

    @Test
    public void createNewAccountTest() throws Exception {    
        accountService.saveOrUpdateAccount(new Account("testAccount1", 100.00), "usertest");
        assertEquals(accountService.getAccountByNumber("testAccount1", "usertest").getBalance(), 100.00);
    }
    
    @Test
    public void addTransactionToAccountTest() throws Exception {
    	Transaction transaction1 = new Transaction(TransactionType.DEBIT, 50.00, "shopping", TransactionCategory.SHOPPING);
    	Account account1 = new Account("testAccount1", 100.00);
    	accountService.saveOrUpdateAccount(account1, "usertest");
    	transactionService.addTransactionToAccount(transaction1, "testAccount1", "usertest");
    	assertEquals(account1.getBalance(), 50.00);
    	
    }

}
