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
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private TransactionType type;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date transactionDate;
	
	private Double amount;
	
	private String description;
	
	private TransactionCategory category;
	
	@ManyToOne
	private Account account;

	public Transaction() {
		super();
	}
	
	public Transaction(TransactionType type, Double amount, String description, TransactionCategory category) {
		super();
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Date getDate() {
		return transactionDate;
	}

	public void setDate(Date date) {
		this.transactionDate = date;
	}
	
	@PrePersist
	protected void onCreate() {
		this.transactionDate = new Date();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TransactionCategory getCategory() {
		return category;
	}

	public void setCategory(TransactionCategory category) {
		this.category = category;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
	
	
	

}
