package com.sam.iisib24.model;
import org.springframework.stereotype.Component;
@Component

public class Archimed {
    private double density;
    private double volume;
    private double mass;
    private static double freeFallAcceleration = 9.81;

    public Archimed() {}

    public void setDensity(double density) { this.density = density; }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double calculatedArchimedForce() {
        return density * volume * freeFallAcceleration;
    }
    public double calculatedGravityForce() { return mass * freeFallAcceleration; }

}

