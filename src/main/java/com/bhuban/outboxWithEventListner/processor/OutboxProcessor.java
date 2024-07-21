package com.bhuban.outboxWithEventListner.processor;

import com.bhuban.outboxWithEventListner.entity.Outbox;

public interface OutboxProcessor {
    void process(Outbox task);
}
