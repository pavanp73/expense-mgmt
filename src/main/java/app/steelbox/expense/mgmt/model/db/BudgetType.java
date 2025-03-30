package app.steelbox.expense.mgmt.model.db;

import app.steelbox.expense.mgmt.model.db.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "exp_budget_type")
public class BudgetType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "budget_type", nullable = false, unique = true)
    private String budgetType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String name) {
        this.budgetType = name;
    }
}
