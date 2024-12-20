package org.reactive_java;


import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.reactive_java.benchmark.BenchmarkOne;
import org.reactive_java.benchmark.BenchmarkTwo;

public final class JmhBenchmarkRunner {

    private JmhBenchmarkRunner() {}

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchmarkTwo.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("lab3.2_benchmark_result_run_3.csv")
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
