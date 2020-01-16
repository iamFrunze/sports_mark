package com.byfrunze.sportsball;

import java.util.List;

public class ModelOfBall {
    private List<Double> strength;
    private List<Double> stamina;
    private List<Double> speed;
    private List<Double> warrior;
    private List<Double> agility;

    public ModelOfBall(List<Double> strength, List<Double> stamina, List<Double> speed, List<Double> warrior, List<Double> agility) {
        this.strength = strength;
        this.stamina = stamina;
        this.speed = speed;
        this.warrior = warrior;
        this.agility = agility;
    }

    public List<Double> getStrength() {
        return strength;
    }

    public void setStrength(List<Double> strength) {
        this.strength = strength;
    }

    public List<Double> getStamina() {
        return stamina;
    }

    public void setStamina(List<Double> stamina) {
        this.stamina = stamina;
    }

    public List<Double> getSpeed() {
        return speed;
    }

    public void setSpeed(List<Double> speed) {
        this.speed = speed;
    }

    public List<Double> getWarrior() {
        return warrior;
    }

    public void setWarrior(List<Double> warrior) {
        this.warrior = warrior;
    }

    public List<Double> getAgility() {
        return agility;
    }

    public void setAgility(List<Double> agility) {
        this.agility = agility;
    }
}
