package com.bhuban.outboxWithEventListner.entity;

import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.ColumnTransformer;

import com.bhuban.outboxWithEventListner.event.OutboxEntityListener;
import com.bhuban.outboxWithEventListner.utils.HashMapConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(OutboxEntityListener.class)
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Convert(converter = HashMapConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private Map<String, String> metadata;

    @Convert(converter = HashMapConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private Map<String, String> params;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum TaskType {
        EMAIL_NOTIFICATION,
        SMS_NOTIFICATION,
    }

    public enum Status {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        SUPPRESSED
    }

}
