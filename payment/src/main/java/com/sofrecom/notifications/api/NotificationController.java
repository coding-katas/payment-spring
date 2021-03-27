package com.sofrecom.notifications.api;

import com.sofrecom.notifications.domain.HelloMessage;
import com.sofrecom.notifications.domain.PaymentAddedNotification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {


    @MessageMapping("/payment-add")
    @SendTo("/topic/greetings")
    public HelloMessage welcomeUser(PaymentAddedNotification paymentAddedNotification) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new HelloMessage("Hello, " + paymentAddedNotification.getName() + "!");
    }

}