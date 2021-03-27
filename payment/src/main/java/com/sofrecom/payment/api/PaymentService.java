package com.sofrecom.payment.api;

import com.sofrecom.payment.domain.Payment;

public interface PaymentService {
    boolean pay(Payment payment);
}
