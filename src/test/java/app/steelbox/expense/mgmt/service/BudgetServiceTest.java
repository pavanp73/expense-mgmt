package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.Budget;
import app.steelbox.expense.mgmt.model.db.BudgetDetail;
import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.model.enums.BudgetType;
import app.steelbox.expense.mgmt.model.view.BudgetDetailDto;
import app.steelbox.expense.mgmt.model.view.BudgetDto;
import app.steelbox.expense.mgmt.repository.BudgetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class BudgetServiceTest {

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private BudgetDetailService budgetDetailService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private BudgetService budgetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBudgetWithoutBudgetDetails() {

        BudgetDto budgetDto = new BudgetDto();
        budgetDto.setName(UUID.randomUUID().toString());
        budgetDto.setMonth(4);
        budgetDto.setYear(2025);
        budgetDto.setBudgetType(BudgetType.JOINT);
        budgetDto.setBudgetAmount(1000.0);

        Budget budget = mockBudget(budgetDto);
        Mockito.when(budgetRepository.save(any())).thenReturn(budget);

        BudgetDto result = budgetService.createBudget(budgetDto);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getName(), budgetDto.getName());
    }

    @Test
    public void testCreateBudgetWithBudgetDetails() {

        String categoryName = "category" + UUID.randomUUID().toString();
        Category category = new Category();
        category.setName(categoryName);
        category.setId(new Random().nextLong());
        BudgetDto budgetDto = new BudgetDto();
        budgetDto.setName(UUID.randomUUID().toString());
        budgetDto.setMonth(4);
        budgetDto.setYear(2025);
        budgetDto.setBudgetType(BudgetType.JOINT);
        budgetDto.setBudgetAmount(1000.0);
        BudgetDetailDto budgetDetailDto = new BudgetDetailDto();
        budgetDetailDto.setCategory(categoryName);
        budgetDetailDto.setAmount(1000.0);
        budgetDto.setBudgetDetailDtoList(List.of(budgetDetailDto));

        Budget budget = mockBudget(budgetDto);
        Mockito.when(budgetRepository.save(any())).thenReturn(budget);
        List<BudgetDetail> budgetDetailList = mockBudgetDetailList(budget, budgetDto.getBudgetDetailDtoList(), category);
        Mockito.when(categoryService.findCategoryByName(categoryName)).thenReturn(category);
        Mockito.when(budgetDetailService.createBudgetDetails(any())).thenReturn(budgetDetailList);


        BudgetDto result = budgetService.createBudget(budgetDto);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getName(), budgetDto.getName());
        assertNotNull(result.getBudgetDetailDtoList());
        assertEquals(result.getBudgetDetailDtoList().size(), budgetDetailList.size());
    }

    private Budget mockBudget(BudgetDto budgetDto) {
        Budget budget = new Budget();
        budget.setId(new Random().nextLong());
        budget.setName(budgetDto.getName());
        budget.setBudgetType(budgetDto.getBudgetType());
        budget.setMonth(budgetDto.getMonth());
        budget.setYear(budgetDto.getYear());
        budget.setBudgetAmount(budgetDto.getBudgetAmount());
        return budget;
    }

    private List<BudgetDetail> mockBudgetDetailList(Budget budget,
                                                    List<BudgetDetailDto> budgetDetailDtoList,
                                                    Category category) {
        return budgetDetailDtoList.stream()
                .map(budgetDetailDto -> {
                    BudgetDetail budgetDetail = new BudgetDetail();
                    budgetDetail.setBudget(budget);
                    budgetDetail.setAmount(budgetDetailDto.getAmount());
                    budgetDetail.setId(new Random().nextLong());
                    budgetDetail.setCategory(category);
                    return budgetDetail;
                }).toList();
    }

}
