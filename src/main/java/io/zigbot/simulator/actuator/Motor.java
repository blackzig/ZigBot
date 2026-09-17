package io.zigbot.simulator.actuator;

public interface Motor {

    void setOutput(double output);

    double output();

    double velocity();
}
