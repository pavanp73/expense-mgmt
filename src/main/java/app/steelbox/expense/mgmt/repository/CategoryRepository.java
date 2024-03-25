package app.steelbox.expense.mgmt.repository;

import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("category")
public interface CategoryRepository extends BaseRepository<Category, Integer> {

    Category findByName(String name);

}
