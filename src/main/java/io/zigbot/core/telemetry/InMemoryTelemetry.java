package io.zigbot.core.telemetry;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class InMemoryTelemetry implements TelemetrySink {

    private final Map<String, Object> values = new LinkedHashMap<>();

    @Override
    public void putNumber(String key, double value) {
        requireKey(key);
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Telemetry number must be finite.");
        }
        values.put(key, value);
    }

    @Override
    public void putText(String key, String value) {
        requireKey(key);
        values.put(key, Objects.requireNonNull(value, "Telemetry text is required."));
    }

    @Override
    public void putBoolean(String key, boolean value) {
        requireKey(key);
        values.put(key, value);
    }

    public Object get(String key) {
        return values.get(key);
    }

    public Map<String, Object> snapshot() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(values));
    }

    public void clear() {
        values.clear();
    }

    private static void requireKey(String key) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Telemetry key is required.");
        }
    }
}
