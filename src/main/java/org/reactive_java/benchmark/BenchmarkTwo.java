package org.reactive_java.benchmark;

import org.openjdk.jmh.annotations.*;
import org.reactive_java.benchmark.stats_collector.FlowableStatsCollector;
import org.reactive_java.generator.TaskGenerator;
import org.reactive_java.model.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class BenchmarkTwo {
    @Param({"100000", "150000","200000", "250000", "300000"})
    private int tasksAmount;

    private List<Task> tasks;

    @Setup(Level.Invocation)
    public void setUp() {
        tasks = TaskGenerator.generateTasks(tasksAmount);
    }

    @Benchmark
    public void flowableStatsBenchmark() {
        FlowableStatsCollector.collectStats(tasks);
    }
}
