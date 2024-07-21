package com.bhuban.outboxWithEventListner.service.impl;

import org.springframework.stereotype.Service;

import com.bhuban.outboxWithEventListner.dto.UserRequest;
import com.bhuban.outboxWithEventListner.entity.UserEntity;
import com.bhuban.outboxWithEventListner.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final NotificationService notificationService;

    public boolean save(UserRequest request) {
        try {
            UserEntity user = UserEntity.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .phone(request.getPhone())
                    .build();
            repository.save(user);
            log.info("User created sucessfully with name: {}, email : {} and phone: {}", request.getFullName(),
                    request.getEmail(), request.getPhone());
            // send otp verification code
            notificationService.sendOtpVerification(user);
            return true;
        } catch (Exception e) { // handle exception as per requirement
            log.error("Error occured while saving user: ", e);
            return false;
        }
    }
}
