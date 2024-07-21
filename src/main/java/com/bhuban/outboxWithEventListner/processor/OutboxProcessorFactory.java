package com.bhuban.outboxWithEventListner.processor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.bhuban.outboxWithEventListner.entity.Outbox;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutboxProcessorFactory {
    private final ApplicationContext applicationContext;

    public OutboxProcessor getProcessor(Outbox.TaskType taskType) throws Exception {
        return applicationContext.getBean(taskType.toString(), OutboxProcessor.class);
    }
}