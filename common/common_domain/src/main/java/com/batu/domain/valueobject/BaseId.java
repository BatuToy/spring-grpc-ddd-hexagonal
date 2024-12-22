package com.batu.domain.valueobject;

public abstract class BaseId<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public BaseId(T value){
        this.value = value;
    }
}
