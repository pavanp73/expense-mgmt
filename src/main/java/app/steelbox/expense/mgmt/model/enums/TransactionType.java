package app.steelbox.expense.mgmt.model.enums;

public enum TransactionType {

    INCOME("Income"),
    EXPENSE("Expense");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
