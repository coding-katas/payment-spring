package com.sofrecom.payment.service;

import com.sofrecom.payment.api.PaymentService;
import com.sofrecom.payment.domain.Payment;
import com.sofrecom.payment.infrastructure.configuration.PaymentServiceSettings;
import com.sofrecom.payment.infrastructure.persistance.PaymentRepository;
import com.sofrecom.eventbus.api.EventBus;
import com.sofrecom.eventbus.domain.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ExternalPaymentService implements PaymentService {
    static Logger log = Logger.getLogger(ExternalPaymentService.class.getName());

    @Autowired
    MeterRegistry meterRegistry;

    private final PaymentServiceSettings paymentServiceSettings;
    private final PaymentRepository paymentRepository;
    private EventBus eventBus;

    @Autowired
    public ExternalPaymentService(PaymentServiceSettings paymentServiceSettings,
                                  PaymentRepository paymentRepository,
                                  EventBus eventBus) {
        this.paymentServiceSettings = paymentServiceSettings;
        this.paymentRepository = paymentRepository;
        this.eventBus = eventBus;
    }

    @Override
    public boolean pay(Payment payment) {
        if (paymentServiceSettings.getSupportedAccounts().contains(payment.getAccountTo())) {
            meterRegistry.counter("book_success").increment();
            paymentRepository.save(payment);
            eventBus.publish(new Event("SAVED", "Saved payment " + payment));
            return true;
        }
        meterRegistry.counter("book_failed").increment();
        eventBus.publish(new Event("REJECTED", "Rejected payment " + payment));
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("in init method");
    }

    @PreDestroy
    public void cleanup() {
        log.info("in cleanup method. Possible to release some resources");
    }
}
