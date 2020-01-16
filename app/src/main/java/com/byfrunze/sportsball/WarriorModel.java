package com.byfrunze.sportsball;

import java.util.List;

public class WarriorModel {
    private int age;
    private int weight;
    private Double strength;
    private Double stamina;
    private Double speed;
    private Double warrior;
    private Double agility;

    public WarriorModel(int age, int weight, Double strength, Double stamina, Double speed, Double warrior, Double agility) {
        this.age = age;
        this.weight = weight;
        this.strength = strength;
        this.stamina = stamina;
        this.speed = speed;
        this.warrior = warrior;
        this.agility = agility;
    }

    public WarriorModel() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Double getStrength() {
        return strength;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public Double getStamina() {
        return stamina;
    }

    public void setStamina(Double stamina) {
        this.stamina = stamina;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getWarrior() {
        return warrior;
    }

    public void setWarrior(Double warrior) {
        this.warrior = warrior;
    }

    public Double getAgility() {
        return agility;
    }

    public void setAgility(Double agility) {
        this.agility = agility;
    }
}
