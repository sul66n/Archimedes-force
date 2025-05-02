package com.sam.iisib24.model;

public class Archimed {
    private double density;
    private double volume;
    private double mass;
    private static double freeFallAcceleration = 9.81;

    public Archimed() {}
    public Archimed(double density, double volume, double mass) {
        this.density = density;
        this.volume = volume;
        this.mass = mass;
    }
    public double calculatedArchimedForce() {
        return density * volume * freeFallAcceleration;
    }
    public double calculatedGravityForce() { return mass * freeFallAcceleration; }


}

