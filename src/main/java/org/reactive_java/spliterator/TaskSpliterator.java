package org.reactive_java.spliterator;

import org.reactive_java.model.Task;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import static org.reactive_java.util.Constants.SPLITERATOR_SIZE_LIMIT;

public class TaskSpliterator implements Spliterator<Task> {
    private final List<Task> tasks;
    private int index;
    private final int length;

    public TaskSpliterator(List<Task> tasks) {
        this.tasks = tasks;
        this.index = 0;
        this.length = tasks.size();
    }

    @Override
    public boolean tryAdvance(Consumer<? super Task> action) {
        action.accept(tasks.get(index++));
        return index == length;
    }

    @Override
    public Spliterator<Task> trySplit() {
        if (length - index > SPLITERATOR_SIZE_LIMIT) {
            var position = (index + length) / 2;
            var sublist = tasks.subList(index, position);
            index = position;
            return new TaskSpliterator(sublist);
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return length - index;
    }

    @Override
    public int characteristics() {
        return IMMUTABLE | NONNULL;
    }
}
