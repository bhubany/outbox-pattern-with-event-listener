package com.bhuban.outboxWithEventListner.service;

import java.util.Map;

public interface EmailService {
    public boolean send(String to, Map<String, String> params);
}
