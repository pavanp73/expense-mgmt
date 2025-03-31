package app.steelbox.expense.mgmt.repository;

import app.steelbox.expense.mgmt.model.db.BudgetDetail;
import app.steelbox.expense.mgmt.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetDetailRepository extends BaseRepository<BudgetDetail, Long> {
}
