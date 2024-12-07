package org.reactive_java.collector;

import org.reactive_java.model.Evaluation;
import org.reactive_java.model.Task;
import org.reactive_java.model.User;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class ImprovedConcurrentUserTasksCompletionCollector implements Collector<Task, Map<User, Map<Task, Boolean>>, Map<User, Map<Task, Boolean>>> {
    private final boolean useDelay;

    public ImprovedConcurrentUserTasksCompletionCollector(boolean useDelay) {
        this.useDelay = useDelay;
    }

    public static ImprovedConcurrentUserTasksCompletionCollector toTaskStatusStatsMap(boolean useDelay) {
        return new ImprovedConcurrentUserTasksCompletionCollector(useDelay);
    }

    @Override
    public Supplier<Map<User, Map<Task, Boolean>>> supplier() {
        return ConcurrentHashMap::new;
    }

    @Override
    public BiConsumer<Map<User, Map<Task, Boolean>>, Task> accumulator() {
        return (map, task) -> {
            User user;
            try {
                user = task.getUser(useDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Evaluation evaluation = task.getEvaluation();

            boolean taskCompletedOnTime = task.getStatuses()
                    .stream()
                    .noneMatch(
                            taskStatus -> Duration.between(taskStatus.startTime(), taskStatus.finishTime())
                                    .compareTo(evaluation.getStatusDurationMap().get(taskStatus.status())) > 0
                    );

            if (map.containsKey(user)) {
                map.get(user).put(task, taskCompletedOnTime);
            } else {
                var resultMap = new HashMap<Task, Boolean>();
                resultMap.put(task, taskCompletedOnTime);
                map.put(user, resultMap);
            }
        };
    }

    @Override
    public BinaryOperator<Map<User, Map<Task, Boolean>>> combiner() {
        return (map1, map2) -> {
            map2.forEach((user, value) -> {
                if (map1.containsKey(user)) {
                    map1.get(user).putAll(value);
                } else {
                    map1.put(user, value);
                }
            });
            return map1;
        };
    }

    @Override
    public Function<Map<User, Map<Task, Boolean>>, Map<User, Map<Task, Boolean>>> finisher() {
        return Collections::unmodifiableMap;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED, IDENTITY_FINISH);
    }
}