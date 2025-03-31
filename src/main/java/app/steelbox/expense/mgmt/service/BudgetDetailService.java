package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.BudgetDetail;
import app.steelbox.expense.mgmt.repository.BudgetDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetDetailService {

    private final BudgetDetailRepository budgetDetailRepository;

    @Autowired
    public BudgetDetailService(BudgetDetailRepository budgetDetailRepository) {
        this.budgetDetailRepository = budgetDetailRepository;
    }

    public List<BudgetDetail> createBudgetDetails(List<BudgetDetail> budgetDetailList) {
        return budgetDetailRepository.saveAll(budgetDetailList);
    }
}
