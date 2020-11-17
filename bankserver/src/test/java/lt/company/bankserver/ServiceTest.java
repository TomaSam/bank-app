package lt.company.bankserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.Transaction;
import lt.company.bankserver.model.TransactionCategory;
import lt.company.bankserver.model.TransactionType;
import lt.company.bankserver.service.AccountService;
import lt.company.bankserver.service.TransactionService;

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
        accountService.saveOrUpdateAccount("usertest");
        assertEquals(accountService.getAccountByNumber("testAccount1", "usertest").getBalance(), 0.00);
    }
    
    @Test
    public void addTransactionToAccountTest() throws Exception {
    	Transaction transaction1 = new Transaction(TransactionType.DEBIT, 50.00, "shopping", TransactionCategory.SHOPPING);
    	Account account1 = new Account();
    	accountService.saveOrUpdateAccount("usertest");
    	transactionService.addTransactionToAccount(transaction1, "testAccount1", "usertest");
    	assertEquals(account1.getBalance(), 50.00);
    	
    }

}
