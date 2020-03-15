package com.byfrunze.sportsball.models;

public class ModelWarriorMark {
    private int sex;
    private int category;
    private int age;
    private int countExerc;
    private int strength;
    private int speed;
    private int stamina;
    private int ws;
    private int agility;

    public ModelWarriorMark(int sex, int category, int age, int countExerc, int strength, int speed, int stamina, int ws, int agility) {
        this.sex = sex;
        this.category = category;
        this.age = age;
        this.countExerc = countExerc;
        this.strength = strength;
        this.speed = speed;
        this.stamina = stamina;
        this.ws = ws;
        this.agility = agility;
    }



    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCountExerc() {
        return countExerc;
    }

    public void setCountExerc(int countExerc) {
        this.countExerc = countExerc;
    }
}
