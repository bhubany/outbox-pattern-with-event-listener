package com.bhuban.outboxWithEventListner.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.bhuban.outboxWithEventListner.processor.OutboxProcessor;
import com.bhuban.outboxWithEventListner.processor.OutboxProcessorFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxEventListener {
    private final OutboxProcessorFactory factory;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void listen(OutboxEvent event) {
        try {
            OutboxProcessor processor = factory.getProcessor(event.getTask().getTaskType());
            processor.process(event.getTask());
        } catch (Exception e) {
            log.error("Exception occured while processing task : ", e);
        }
    }
}
