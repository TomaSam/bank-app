package lt.company.bankserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false, unique = true)
	private String number;
	
	private Double balance;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(updatable = false)
	private Date created_At;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updated_At;
	
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="account", fetch = FetchType.LAZY)
	private List<Expenses> expenses = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="account", fetch = FetchType.LAZY)
	private List<Income> incomes = new ArrayList<>();
	
	public Account() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}
	
	public void addExpenses(Expenses expense) {
		this.expenses.add(expense);
		expense.setAccount(this);
	}

	public List<Expenses> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}

	public void addIncome(Income income) {
		this.incomes.add(income);
		income.setAccount(this);
	}
	
	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}
	
	

}
