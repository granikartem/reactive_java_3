package org.reactive_java.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Mode;
import org.reactive_java.benchmark.stats_collector.*;
import org.reactive_java.generator.TaskGenerator;
import org.reactive_java.model.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class BenchmarkOne {
    @Param({"true"})
    private boolean useDelay;
    @Param({"500", "2000"})
    private int tasksAmount;

    private List<Task> tasks;

    @Setup(Level.Invocation)
    public void setUp() {
        tasks = TaskGenerator.generateTasks(tasksAmount);
    }

    @Benchmark
    public void concurrentCollectorStatsBenchmark() {
        ConcurrentStatsCollector.collectStats(tasks, useDelay);
    }

    @Benchmark
    public void improvedConcurrentCollectorStatsBenchmark() {
        ImprovedConcurrentStatsCollector.collectStats(tasks, useDelay);
    }

    @Benchmark
    public void observableStatsBenchmark() {
        ObservableStatsCollector.collectStats(tasks, useDelay);
    }
}
