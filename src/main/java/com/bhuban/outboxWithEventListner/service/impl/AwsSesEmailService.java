package com.bhuban.outboxWithEventListner.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bhuban.outboxWithEventListner.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * This class is responsible for sending emails using AWS SES (Simple Email Service).
 * It implements the EmailService interface and handles the actual email sending logic.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsSesEmailService implements EmailService {

    @Override
    public boolean send(String to, Map<String, String> params) {
        // TODO: Implement the actual email sending logic using AWS SES

        try {
            log.info("Sending email using AWS SES");
            log.info("Email sent to: {} with details: {}", to, params.toString());
            return true;
        } catch (Exception e) {
            log.error("Error occurred while sending email using AWS SES", e);
            return false;
        }
    }
}
