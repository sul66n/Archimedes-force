package com.sam.iisib24.model;

import org.springframework.stereotype.Component;


@Component
public class LawOfMomentum {
    private double speedBeforeFirst;
    private double speedBeforeSecond;
    private double massFirst;
    private double massSecond;

    // Установки значений для данных
    public void setSpeedBeforeFirst(double speedBeforeFirst) {
        this.speedBeforeFirst = speedBeforeFirst;
    }

    public void setSpeedBeforeSecond(double speedBeforeSecond) {
        this.speedBeforeSecond = speedBeforeSecond;
    }

    public void setMassFirst(double massFirst) {
        this.massFirst = massFirst;
    }

    public void setMassSecond(double massSecond) {
        this.massSecond = massSecond;
    }

    // Метод для расчета импульса
    public double calculatedMomentum() {
        // Например, скорость после столкновения двух объектов
        return (massFirst * speedBeforeFirst + massSecond * speedBeforeSecond) / (massFirst + massSecond);
    }

    // Метод для расчета скорости первого объекта после столкновения
    public double calculateSpeedAfterFirst() {
        return (massFirst - massSecond) * speedBeforeFirst + 2 * massSecond * speedBeforeSecond;
    }

    // Метод для расчета скорости второго объекта после столкновения
    public double calculateSpeedAfterSecond() {
        return 2 * massFirst * speedBeforeFirst + (massSecond - massFirst) * speedBeforeSecond;
    }


}

