package app.steelbox.expense.mgmt.model.enums;

public enum PayMethod {

    DEBIT_CARD("Debit Card"),
    CREDIT_CARD("Credit Card"),
    PHONE_PE("PhonePe"),
    AMAZON_PAY("AmazonPay"),
    CRED("Cred"),
    INTERNET_BANKING("Internet_Banking"),
    CASH("Cash");

    private final String value;

    PayMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
