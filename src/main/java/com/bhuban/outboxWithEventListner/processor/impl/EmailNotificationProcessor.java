package com.bhuban.outboxWithEventListner.processor.impl;

import org.springframework.stereotype.Service;

import com.bhuban.outboxWithEventListner.entity.Outbox;
import com.bhuban.outboxWithEventListner.processor.OutboxProcessor;
import com.bhuban.outboxWithEventListner.service.EmailService;
import com.bhuban.outboxWithEventListner.service.impl.OutboxService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("EMAIL_NOTIFICATION")
@RequiredArgsConstructor
public class EmailNotificationProcessor implements OutboxProcessor {
    private final EmailService emailService;
    private final OutboxService outboxService;

    /*
     * Processes an outbox task by sending an email and updating the task status.
     * 
     * @param task the outbox task to be processed, which contains email details and
     * parameters.
     */
    @Override
    public void process(Outbox task) {
        try {
            boolean isEmailSent = emailService.send(task.getMetadata().get("to"), task.getParams());
            Outbox.Status status = isEmailSent ? Outbox.Status.COMPLETED : Outbox.Status.FAILED;
            outboxService.updateStatus(task.getId(), status);
        } catch (Exception e) {
            log.error("Error occured while processing notification task: ", e);
        }
    }

}
