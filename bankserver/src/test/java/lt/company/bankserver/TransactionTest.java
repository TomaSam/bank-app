package lt.company.bankserver;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.Transaction;
import lt.company.bankserver.model.TransactionCategory;
import lt.company.bankserver.model.TransactionType;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionTest {
	
//	@Autowired
//	private WebApplicationContext context; 
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@BeforeEach
//	public void setup() {
//		mockMvc = MockMvcBuilders
//				.webAppContextSetup(context)
//				.apply(springSecurity())
//				.build();
//	}
//	
//	@Test
//	public void testTransactionbetweenAccounts() throws Exception {
//		
//		new Account("useraccount1", 100.00);
//		new Account("useraccount2", 0.0);
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.post("/api/transaction/{number}/{nextNumber}", "useraccount1", "useraccount2")
//				.with(user("user"))
//				.content(asJsonString(new Transaction(TransactionType.CREDIT, 78.00, "pavedimas", TransactionCategory.PAYMENT)))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated());		
//	}
//	
//	public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
