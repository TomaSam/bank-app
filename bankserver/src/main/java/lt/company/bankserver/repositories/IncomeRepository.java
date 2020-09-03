package lt.company.bankserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.company.bankserver.model.Income;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {

}
