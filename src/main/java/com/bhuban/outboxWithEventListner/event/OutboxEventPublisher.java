package com.bhuban.outboxWithEventListner.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.bhuban.outboxWithEventListner.entity.Outbox;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(Outbox task) {
        applicationEventPublisher.publishEvent(new OutboxEvent(this, task));
    }
}
