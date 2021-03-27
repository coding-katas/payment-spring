package com.sofrecom.payment.api;

import com.sofrecom.payment.domain.Payment;
import com.sofrecom.payment.domain.PaymentDto;
import com.sofrecom.payment.infrastructure.persistance.PaymentRepository;
import com.sofrecom.eventbus.api.EventBus;
import com.sofrecom.eventbus.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class MVCController {

  @Autowired
  private PaymentRepository paymentRepository;

  @PostConstruct
  private void init() {
    paymentRepository.save(new Payment("T1", "dummyFrom", "dummyTo", 100L));
  }

  @Autowired
  private EventBus eventBus;

  @RequestMapping("/mvc/all-payments")
  public String indexView(Model model) {
    model.addAttribute("list", paymentRepository.findAll());
    return "allPayments";
  }

  @PostMapping("/mvc/payment")
  public String paymentSubmit(@ModelAttribute PaymentDto paymentDto, Model model) {
    paymentRepository.save(new Payment(paymentDto.getUserId(), paymentDto.getAccountFrom(), paymentDto.getAccountTo(), paymentDto.getAmount()));
    eventBus.publish(new Event("SAVE", "Save payment" + paymentDto));
    model.addAttribute("list", paymentRepository.findAll());
    return "allPayments";
  }

  @GetMapping("/mvc/createPayment")
  public String paymentForm(Model model) {
    model.addAttribute("paymentDto", new PaymentDto());
    return "create";
  }
}
