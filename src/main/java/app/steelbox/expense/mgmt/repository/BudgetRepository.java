package app.steelbox.expense.mgmt.repository;

import app.steelbox.expense.mgmt.model.db.Budget;
import app.steelbox.expense.mgmt.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends BaseRepository<Budget, Long> {
}
