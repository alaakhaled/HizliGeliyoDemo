package com.aabusabra.hizligeliyodemo.tasks;


public abstract class BaseTask<T> implements Runnable {
    public abstract T getResult();

    public abstract boolean postResult(T result);
}

