package app.steelbox.expense.mgmt.model.shared;

import java.io.Serializable;
import java.util.List;

public class MonthlyChart implements Serializable {

    private Double total;
    private List<ChartData> categoryWiseData;
    private String month;
    private Integer year;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ChartData> getCategoryWiseData() {
        return categoryWiseData;
    }

    public void setCategoryWiseData(List<ChartData> categoryWiseData) {
        this.categoryWiseData = categoryWiseData;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
