package org.reactive_java.benchmark.stats_collector;

import org.reactive_java.collector.rx.ObservableCollector;
import org.reactive_java.model.Task;
import org.reactive_java.model.User;

import java.util.List;
import java.util.Map;

public class ObservableStatsCollector {
    public static Map<User, Map<Task, Boolean>> collectStats(List<Task> tasks, boolean useDelay) {
        return ObservableCollector.processTasks(tasks, useDelay).blockingFirst();
    }
}
