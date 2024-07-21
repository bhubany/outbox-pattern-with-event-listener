package com.bhuban.outboxWithEventListner.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhuban.outboxWithEventListner.entity.Outbox;
import com.bhuban.outboxWithEventListner.repository.OutboxRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutboxService {
    private final OutboxRepository repository;

    public Outbox save(Outbox.TaskType taskType, Map<String, String> metadata, Map<String, String> params) {
        Outbox outbox = Outbox.builder()
                .metadata(metadata)
                .params(params)
                .status(Outbox.Status.PENDING)
                .taskType(taskType)
                .build();
        return repository.save(outbox);
    }

    public Outbox findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void updateStatus(UUID id, Outbox.Status status) throws Exception {
        Outbox outbox = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No task found on outbox for ID: " + id));
        outbox.setStatus(status);
    }

}
