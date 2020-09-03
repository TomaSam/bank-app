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
public class Income {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double incomeAmount;
	
	private String title;
	
	private IncomeCategory category;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date income_date;

	@ManyToOne
	private Account account;
	
	
	public Income() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(Double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IncomeCategory getCategory() {
		return category;
	}

	public void setCategory(IncomeCategory category) {
		this.category = category;
	}

	public Date getIncome_date() {
		return income_date;
	}

	public void setIncome_date(Date income_date) {
		this.income_date = income_date;
	}
	
	@PrePersist
	protected void onCreate() {
		this.income_date = new Date();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
