package com.bhuban.outboxWithEventListner.event;

import org.springframework.stereotype.Component;

import com.bhuban.outboxWithEventListner.entity.Outbox;

import jakarta.persistence.PostPersist;
import lombok.RequiredArgsConstructor;

/*
 * This class listens for new entries being saved to the "Outbox" table.
 * When a new entry is saved, it automatically sends this entry to the event publisher.
 */

@Component
@RequiredArgsConstructor
public class OutboxEntityListener {
    private final OutboxEventPublisher publisher;

    @PostPersist
    public void onPostPersist(Outbox outbox) {
        publisher.publish(outbox);
    }
}
