package app.steelbox.expense.mgmt.repository;

import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository("type-lookup")
public interface TypeLookupRepository extends BaseRepository<TypeLookup, Integer> {

    TypeLookup findByType(String type);
}
