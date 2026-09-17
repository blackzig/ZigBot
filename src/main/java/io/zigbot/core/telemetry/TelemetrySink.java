package io.zigbot.core.telemetry;

public interface TelemetrySink {

    void putNumber(String key, double value);

    void putText(String key, String value);

    void putBoolean(String key, boolean value);
}
