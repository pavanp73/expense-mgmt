package app.steelbox.expense.mgmt.model.shared;

import java.io.Serializable;

public class ChartData implements Serializable {

    private String category;
    private Double totalAmount;
    private Integer transactionsCount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTransactionsCount() {
        return transactionsCount;
    }

    public void setTransactionsCount(Integer transactionsCount) {
        this.transactionsCount = transactionsCount;
    }
}
