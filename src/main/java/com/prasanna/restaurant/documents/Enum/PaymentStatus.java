package com.prasanna.restaurant.documents.Enum;

public enum PaymentStatus {
    PENDING("pending"),
    SUCCESS_FULL("success_full"),
    FAILED("failure");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override public String toString() {
        return status;
    }
}
