package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.Budget;
import app.steelbox.expense.mgmt.model.db.BudgetDetail;
import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.model.view.BudgetDetailDto;
import app.steelbox.expense.mgmt.model.view.BudgetDto;
import app.steelbox.expense.mgmt.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryService categoryService;
    private final BudgetDetailService budgetDetailService;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository,
                         CategoryService categoryService,
                         BudgetDetailService budgetDetailService) {
        this.budgetRepository = budgetRepository;
        this.categoryService = categoryService;
        this.budgetDetailService = budgetDetailService;
    }

    public BudgetDto createBudget(BudgetDto budgetDto) {
        Budget budget = budgetRepository.save(getBudget(budgetDto));
        if (budgetDto.getBudgetDetailDtoList() == null || budgetDto.getBudgetDetailDtoList().isEmpty()) {
            return getBudgetDto(budget, Optional.empty());
        } else {
            List<BudgetDetail> budgetDetailList = budgetDto.getBudgetDetailDtoList().stream()
                    .map(budgetDetailDto -> getBudgetDetails(budget, budgetDetailDto)).toList();
            budgetDetailList = budgetDetailService.createBudgetDetails(budgetDetailList);
            return getBudgetDto(budget, Optional.of(budgetDetailList));
        }
    }

    private BudgetDto getBudgetDto(Budget budget, Optional<List<BudgetDetail>> budgetDetailList) {
        BudgetDto budgetDto = new BudgetDto();
        budgetDto.setId(budget.getId());
        budgetDto.setName(budget.getName());
        budgetDto.setMonth(budget.getMonth());
        budgetDto.setYear(budget.getYear());
        budgetDto.setBudgetType(budget.getBudgetType());
        budgetDto.setBudgetAmount(budget.getBudgetAmount());
        if (budgetDetailList.isPresent()) {
            List<BudgetDetailDto> budgetDetailDtoList = budgetDetailList.get().stream()
                    .map(this::getBudgetDetailDto).toList();
            budgetDto.setBudgetDetailDtoList(budgetDetailDtoList);
        }
        return budgetDto;
    }

    private BudgetDetailDto getBudgetDetailDto(BudgetDetail budgetDetail) {
        BudgetDetailDto budgetDetailDto = new BudgetDetailDto();
        budgetDetailDto.setId(budgetDetail.getId());
        budgetDetailDto.setBudgetId(budgetDetail.getBudget().getId());
        budgetDetailDto.setAmount(budgetDetail.getAmount());
        budgetDetailDto.setCategory(budgetDetail.getCategory().getName());
        return budgetDetailDto;
    }

    private Budget getBudget(BudgetDto budgetDto) {
        Budget budget = new Budget();
        budget.setName(budgetDto.getName());
        budget.setMonth(budgetDto.getMonth());
        budget.setYear(budgetDto.getYear());
        budget.setBudgetType(budgetDto.getBudgetType());
        budget.setBudgetAmount(budgetDto.getBudgetAmount());
        return budget;
    }

    private BudgetDetail getBudgetDetails(Budget budget, BudgetDetailDto budgetDetailDto) {
        Category category = categoryService.findCategoryByName(budgetDetailDto.getCategory());
        BudgetDetail budgetDetail = new BudgetDetail();
        budgetDetail.setCategory(category);
        budgetDetail.setAmount(budgetDetailDto.getAmount());
        budgetDetail.setBudget(budget);
        return budgetDetail;
    }
}
