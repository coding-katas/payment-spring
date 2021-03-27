package com.sofrecom.payment.api;

import com.sofrecom.payment.domain.PaymentAndUser;
import com.sofrecom.payment.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
  List<User> getAllUsers();
  void insert(User user);
  Optional<PaymentAndUser> getPaymentAndUsersForUserId(String userId);
}
