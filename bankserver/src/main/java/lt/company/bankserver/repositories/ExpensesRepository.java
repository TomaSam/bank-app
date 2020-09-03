package lt.company.bankserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.company.bankserver.model.Expenses;

@Repository
public interface ExpensesRepository extends CrudRepository<Expenses, Long> {
	

}
