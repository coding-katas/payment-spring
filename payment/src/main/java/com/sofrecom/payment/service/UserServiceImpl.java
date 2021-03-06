package com.sofrecom.payment.service;

import com.sofrecom.payment.api.UserService;
import com.sofrecom.payment.domain.Payment;
import com.sofrecom.payment.domain.PaymentAndUser;
import com.sofrecom.payment.domain.User;
import com.sofrecom.payment.infrastructure.persistance.PaymentRepository;
import com.sofrecom.payment.infrastructure.persistance.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private PaymentRepository paymentRepository;

  @Override
  public List<User> getAllUsers() {
    return StreamSupport.stream(usersRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public void insert(User user) {
    usersRepository.save(user);
  }

  @Override
  public Optional<PaymentAndUser> getPaymentAndUsersForUserId(String userId) {
    List<User> users = usersRepository.findByName(userId);
    List<Payment> payments = paymentRepository.findByUserId(userId);
    if(users.isEmpty()){
      return Optional.empty();
    }
    return Optional.of(new PaymentAndUser(users.get(0), payments));

  }
}
