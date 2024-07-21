package com.bhuban.outboxWithEventListner.event;

import org.springframework.context.ApplicationEvent;

import com.bhuban.outboxWithEventListner.entity.Outbox;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutboxEvent extends ApplicationEvent {
    private Outbox task;

    public OutboxEvent(Object source, Outbox task) {
        super(source);
        this.task = task;
    }
}
