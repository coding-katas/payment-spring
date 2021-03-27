package com.sofrecom.eventbus.api;

import com.sofrecom.eventbus.domain.Event;

public interface EventBus {
  void publish(Event event);
  Event receive();
}
