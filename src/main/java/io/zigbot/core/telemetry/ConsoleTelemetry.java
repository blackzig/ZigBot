package io.zigbot.core.telemetry;

import java.io.PrintStream;
import java.util.Objects;

public final class ConsoleTelemetry implements TelemetrySink {

    private final PrintStream output;

    public ConsoleTelemetry() {
        this(System.out);
    }

    public ConsoleTelemetry(PrintStream output) {
        this.output = Objects.requireNonNull(output, "Output is required.");
    }

    @Override
    public void putNumber(String key, double value) {
        output.printf("%s=%.6f%n", key, value);
    }

    @Override
    public void putText(String key, String value) {
        output.printf("%s=%s%n", key, value);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        output.printf("%s=%s%n", key, value);
    }
}
