package orders.core;

public enum PaymentMethod {
    CASH         ("płatność gotówką"),
    BANK_TRANSFER("płatność przelewem"),
    CREDIT_CARD  ("płatność kartą"),
    BLIK         ("płatność blikiem"),
    OTHER        ("inna metoda płatności");


    private String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
