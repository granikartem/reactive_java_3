package org.reactive_java.model;

import lombok.RequiredArgsConstructor;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@RequiredArgsConstructor
@State(Scope.Benchmark)
public enum Status {

    WAITING("WAITING", "В ожидании", false),

    IN_PROGRESS("IN_PROGRESS", "В работе", false),

    TESTING("TESTING", "Тестирование", false),

    CLOSED("CLOSED", "Закрыта", true);

    private final String code;

    private final String name;

    private final boolean finalStatus;
}
