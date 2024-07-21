package com.bhuban.outboxWithEventListner.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhuban.outboxWithEventListner.entity.Outbox;

public interface OutboxRepository extends JpaRepository<Outbox, UUID> {

}
