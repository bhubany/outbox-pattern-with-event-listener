package com.bhuban.outboxWithEventListner.processor.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.bhuban.outboxWithEventListner.entity.Outbox;
import com.bhuban.outboxWithEventListner.processor.OutboxProcessor;
import com.bhuban.outboxWithEventListner.service.impl.OutboxService;
import com.bhuban.outboxWithEventListner.service.impl.SmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("SMS_NOTIFICATION")
@RequiredArgsConstructor
public class SmsNotificationProcessor implements OutboxProcessor {
    private final OutboxService outboxService;
    private final SmsService smsService;
    private final Random random = new Random();

    @Override
    public void process(Outbox task) {
        try {
            int otp = 1000 + random.nextInt(9000);
            String message = "Hello " + task.getParams().get("name") + ", \n" + otp + " is your otp verification code";
            boolean isSmsSent = smsService.send(task.getMetadata().get("to"), message);
            Outbox.Status status = isSmsSent ? Outbox.Status.COMPLETED : Outbox.Status.FAILED;
            outboxService.updateStatus(task.getId(), status);
        } catch (Exception e) {
            log.error("Error occured while processing notification task: ", e);
        }
    }

}
