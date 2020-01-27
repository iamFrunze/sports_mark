package com.byfrunze.sportsball.models;

public class ModelOfMark {

    private String strength;
    private String speed;
    private String stamina;
    private String ws;
    private String agility;

    public ModelOfMark(String strength, String speed, String stamina, String ws, String agility) {
        this.strength = strength;
        this.speed = speed;
        this.stamina = stamina;
        this.ws = ws;
        this.agility = agility;
    }

    public ModelOfMark() {
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStamina() {
        return stamina;
    }

    public void setStamina(String stamina) {
        this.stamina = stamina;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getAgility() {
        return agility;
    }

    public void setAgility(String agility) {
        this.agility = agility;
    }
}
