package app.steelbox.expense.mgmt.resource;

import app.steelbox.expense.mgmt.model.view.BudgetDto;
import app.steelbox.expense.mgmt.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetResource {

    private final BudgetService budgetService;

    @Autowired
    public BudgetResource(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public BudgetDto createBudget(@RequestBody BudgetDto budgetDto) {
        return budgetService.createBudget(budgetDto);
    }
}
