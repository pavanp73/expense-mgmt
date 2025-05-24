package app.steelbox.expense.mgmt.model.shared;

public class DateRange {

    private Long startDate;
    private Long endDate;

    public DateRange() {}

    public DateRange(Long startDate, Long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }
}
