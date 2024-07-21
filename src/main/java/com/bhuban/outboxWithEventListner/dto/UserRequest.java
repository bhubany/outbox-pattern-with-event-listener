package com.bhuban.outboxWithEventListner.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRequest {
    String fullName;
    String email;
    String password;
    String phone;
}
