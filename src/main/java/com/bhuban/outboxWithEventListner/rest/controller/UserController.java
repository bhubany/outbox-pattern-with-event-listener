package com.bhuban.outboxWithEventListner.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhuban.outboxWithEventListner.dto.UserRequest;
import com.bhuban.outboxWithEventListner.service.impl.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("")
    public ResponseEntity register(@RequestBody UserRequest request) {
        boolean res = service.save(request);
        if (res) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
