package org.reactive_java.benchmark.stats_collector;

import org.reactive_java.collector.ImprovedConcurrentUserTasksCompletionCollector;
import org.reactive_java.model.Task;
import org.reactive_java.model.User;
import org.reactive_java.spliterator.TaskSpliterator;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class ImprovedConcurrentStatsCollector {
    public static Map<User, Map<Task, Boolean>> collectStats(List<Task> tasks, boolean useDelay) {
        return StreamSupport.stream(
                new TaskSpliterator(tasks),
                true
                )
                .parallel()
                .collect(
                        ImprovedConcurrentUserTasksCompletionCollector.toTaskStatusStatsMap(useDelay)
                );
    }
}
