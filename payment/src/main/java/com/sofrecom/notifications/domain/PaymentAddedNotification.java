package com.sofrecom.notifications.domain;

public class PaymentAddedNotification {
    private String name;

    public PaymentAddedNotification() {
    }

    public PaymentAddedNotification(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
