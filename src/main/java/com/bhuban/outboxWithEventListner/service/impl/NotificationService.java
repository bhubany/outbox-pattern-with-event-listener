package com.bhuban.outboxWithEventListner.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bhuban.outboxWithEventListner.entity.Outbox;
import com.bhuban.outboxWithEventListner.entity.UserEntity;

import lombok.RequiredArgsConstructor;

/*
 * This class handles sending different types of notifications.
 * Eg: send OTP (One-Time Password) verification codes to users
 * via email and SMS.
 */

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final OutboxService outboxService;

    public void sendOtpVerification(UserEntity user) {

        // send otp verification code in email and mobile
        // prepare params to be used on template variables
        Map<String, String> params = new HashMap<>();
        params.put("name", user.getFullName());

        Map<String, String> emailMetadata = new HashMap<>();
        emailMetadata.put("to", user.getEmail());
        outboxService.save(Outbox.TaskType.EMAIL_NOTIFICATION, emailMetadata, params);

        // Send sms notification with otp
        Map<String, String> smsMetadata = new HashMap<>();
        smsMetadata.put("to", user.getPhone());
        outboxService.save(Outbox.TaskType.SMS_NOTIFICATION, smsMetadata, params);
    }

}
