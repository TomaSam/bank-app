package lt.company.bankserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import lt.company.bankserver.controller.UserController;
import lt.company.bankserver.model.Account;
import lt.company.bankserver.model.User;
import lt.company.bankserver.security.JwtAuthenticationEntryPoint;
import lt.company.bankserver.security.JwtTokenProvider;
import lt.company.bankserver.service.AccountService;
import lt.company.bankserver.service.CustomUserDetailsService;
import lt.company.bankserver.service.MapValidationErrorService;
import lt.company.bankserver.service.TransactionService;
import lt.company.bankserver.service.UserService;
import lt.company.bankserver.validator.UserValidator;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class ControllerTest {
	
//	@MockBean
//	private UserService userService;
//	
//	@MockBean
//	private AccountService accountService;
//	
//	@MockBean
//	private MapValidationErrorService mapService;
//	
//	@MockBean
//	private JwtTokenProvider provider;
//	
//	@MockBean
//	private JwtAuthenticationEntryPoint point;
//	
//	@MockBean
//	private CustomUserDetailsService customDetails;
//	
//	@MockBean
//	private UserValidator validator;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	private User user;
//	
//	@BeforeEach
//	public void setup() {
//		this.user = new User();
//		user.setUsername("testUser");
//	}
//	
//	@Test
//	public void testCreateNewAccount() throws Exception {
//		
//		Account account = new Account("testNumber1", 0.0);
//		
//		this.mockMvc.perform(post("/api/account")
//				.with(user("testUser"))
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(asJsonString(account)))
//				.andExpect(status().isCreated())
//				.andExpect(jsonPath("$.number", is(account.getNumber())))
//				.andExpect(jsonPath("$.balance", is(account.getBalance())));
//		
//	}
//	
//	
//	public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

}
