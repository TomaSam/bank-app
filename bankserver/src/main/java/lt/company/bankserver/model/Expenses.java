package lt.company.bankserver.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Expenses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double expensesAmount;
	
	private String title;
	
	private ExpenseCategory category;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expenses_date;
	
	@ManyToOne
	private Account account;
	
	public Expenses() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getExpensesAmount() {
		return expensesAmount;
	}

	public void setExpensesAmount(Double expensesAmount) {
		this.expensesAmount = expensesAmount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ExpenseCategory getCategory() {
		return category;
	}

	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}

	public Date getExpenses_date() {
		return expenses_date;
	}

	public void setExpenses_date(Date expenses_date) {
		this.expenses_date = expenses_date;
	}

	@PrePersist
	protected void onCreate() {
		this.expenses_date = new Date();
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
	

}
