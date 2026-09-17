package io.zigbot.core;

public interface Subsystem {

    default void periodic(double dtSeconds) {
    }
}
