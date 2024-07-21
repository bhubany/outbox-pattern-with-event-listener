package com.bhuban.outboxWithEventListner.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhuban.outboxWithEventListner.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
