package app.steelbox.expense.mgmt.model.enums;

public enum Type {

    INCOME("Income"),
    EXPENSE("Expense");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
