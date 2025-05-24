package app.steelbox.expense.mgmt.repository;

import app.steelbox.expense.mgmt.model.db.Transaction;
import app.steelbox.expense.mgmt.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("transaction")
public interface TransactionRepository extends BaseRepository<Transaction, Long> {

    List<Transaction> findByTimestampBetween(Long start, Long end);
}
