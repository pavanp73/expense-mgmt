package app.steelbox.expense.mgmt.model.view;

import app.steelbox.expense.mgmt.model.enums.BudgetType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetDto {

    private Long id;
    private String name;
    private int month;
    private int year;
    private BudgetType budgetType;
    private String description;
    private List<BudgetDetailDto> budgetDetailDtoList;
    private Double budgetAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BudgetType getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(BudgetType budgetType) {
        this.budgetType = budgetType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BudgetDetailDto> getBudgetDetailDtoList() {
        return budgetDetailDtoList;
    }

    public void setBudgetDetailDtoList(List<BudgetDetailDto> budgetDetailDtoList) {
        this.budgetDetailDtoList = budgetDetailDtoList;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
}
