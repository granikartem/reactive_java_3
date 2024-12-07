package org.reactive_java.collector.rx;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;
import org.reactive_java.model.Task;
import org.reactivestreams.Subscription;

public class TaskSubscriber implements FlowableSubscriber<Task> {
    @Override
    public void onSubscribe(@NonNull Subscription s) {

    }

    @Override
    public void onNext(Task task) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
