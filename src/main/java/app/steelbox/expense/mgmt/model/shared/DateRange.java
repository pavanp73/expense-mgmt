package app.steelbox.expense.mgmt.model.shared;

public class DateRange {

    private Long startDate;
    private Long endDate;
    private String month;
    private int year;

    public DateRange() {}

    public DateRange(Long startDate, Long endDate, String month, int year) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.month = month;
        this.year = year;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
