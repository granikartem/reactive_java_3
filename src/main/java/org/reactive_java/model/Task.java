package org.reactive_java.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Task {

    private final long taskId;

    private final String taskNumber;

    private final LocalDateTime createTime;

    private final TaskPriority priority;

    private final List<TaskStatus> statuses;

    private final Evaluation evaluation;

    private User user;

    private String description;

    public User getUser(boolean useDelay) throws InterruptedException {
        if (useDelay) {
            Thread.sleep(Duration.of(1000, ChronoUnit.NANOS));
        }
        return user;
    }
}