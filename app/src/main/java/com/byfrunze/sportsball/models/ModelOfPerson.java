package com.byfrunze.sportsball.models;

import io.realm.RealmObject;

public class ModelOfPerson extends RealmObject {

    private String name;
    private int age;
    private int weight;
    private int sex_id;
    private int category;

    private String date;
    private String points;
    private String mark;

    private String strengthName;
    private String strengthRes;
    private String strengthPoints;

    private String speedName;
    private String speedRes;
    private String speedPoints;

    private String staminaName;
    private String staminaRes;
    private String staminaPoints;

    private String wsName;
    private String wsRes;
    private String wsPoints;

    private String agilityName;
    private String agilityRes;
    private String agilityPoints;

    public void setupModel(ModelOfPerson model) {
        this.name = model.name;
        this.age = model.age;
        this.weight = model.weight;
        this.sex_id = model.sex_id;
        this.category = model.category;
        this.date = model.date;
        this.points = model.points;
        this.mark = model.mark;
        this.strengthName = model.strengthName;
        this.strengthRes = model.strengthRes;
        this.strengthPoints = model.strengthPoints;
        this.speedName = model.speedName;
        this.speedRes = model.speedRes;
        this.speedPoints = model.speedPoints;
        this.staminaName = model.staminaName;
        this.staminaRes = model.staminaRes;
        this.staminaPoints = model.staminaPoints;
        this.wsName = model.wsName;
        this.wsRes = model.wsRes;
        this.wsPoints = model.wsPoints;
        this.agilityName = model.agilityName;
        this.agilityRes = model.agilityRes;
        this.agilityPoints = model.agilityPoints;
    }


    public String getStrengthName() {
        return strengthName;
    }

    public void setStrengthName(String strengthName) {
        this.strengthName = strengthName;
    }

    public String getStrengthRes() {
        return strengthRes;
    }

    public void setStrengthRes(String strengthRes) {
        this.strengthRes = strengthRes;
    }

    public String getStrengthPoints() {
        return strengthPoints;
    }

    public void setStrengthPoints(String strengthPoints) {
        this.strengthPoints = strengthPoints;
    }

    public String getSpeedName() {
        return speedName;
    }

    public void setSpeedName(String speedName) {
        this.speedName = speedName;
    }

    public String getSpeedRes() {
        return speedRes;
    }

    public void setSpeedRes(String speedRes) {
        this.speedRes = speedRes;
    }

    public String getSpeedPoints() {
        return speedPoints;
    }

    public void setSpeedPoints(String speedPoints) {
        this.speedPoints = speedPoints;
    }

    public String getStaminaName() {
        return staminaName;
    }

    public void setStaminaName(String staminaName) {
        this.staminaName = staminaName;
    }

    public String getStaminaRes() {
        return staminaRes;
    }

    public void setStaminaRes(String staminaRes) {
        this.staminaRes = staminaRes;
    }

    public String getStaminaPoints() {
        return staminaPoints;
    }

    public void setStaminaPoints(String staminaPoints) {
        this.staminaPoints = staminaPoints;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getWsRes() {
        return wsRes;
    }

    public void setWsRes(String wsRes) {
        this.wsRes = wsRes;
    }

    public String getWsPoints() {
        return wsPoints;
    }

    public void setWsPoints(String wsPoints) {
        this.wsPoints = wsPoints;
    }

    public String getAgilityName() {
        return agilityName;
    }

    public void setAgilityName(String agilityName) {
        this.agilityName = agilityName;
    }

    public String getAgilityRes() {
        return agilityRes;
    }

    public void setAgilityRes(String agilityRes) {
        this.agilityRes = agilityRes;
    }

    public String getAgilityPoints() {
        return agilityPoints;
    }

    public void setAgilityPoints(String agilityPoints) {
        this.agilityPoints = agilityPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSex_id() {
        return sex_id;
    }

    public void setSex_id(int sex_id) {
        this.sex_id = sex_id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
