package com.prasanna.restaurant.documents.Enum;

public enum PaymentType {
    UPI("upi"),
    CASH("cash"),
    CARD("card");

    private final String type;

    PaymentType(String upi) {
        this.type = upi;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
