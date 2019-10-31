package com.fsmc.app.network.base;

public interface ResponseResultObserver<T> {
    void observe(T response);
}
