package com.bhuban.outboxWithEventListner.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible for sending SMS messages.
 * It provides a method to send SMS to a specified recipient with a given
 * message.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {
    public boolean send(String to, String message) {
        try {
            log.info("SMS sent successfully to: {} with message: {}", to, message);
            return true;
        } catch (Exception e) {
            log.error("Error while sending sms to : {} with message: {}", to, message);
            return false;
        }
    }
}
