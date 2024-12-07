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
        //        var mapSize = map.values().stream().mapToInt(Map::size).sum();
//        if (mapSize > 5000) {
//            System.out.println("Concurrent Stats Collector total tasks in map: " + mapSize);
//        }
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
