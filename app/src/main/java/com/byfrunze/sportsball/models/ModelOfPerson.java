package com.byfrunze.sportsball.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelOfPerson extends RealmObject {

    private String name;
    private int age;
    private int weight;
    private int sex_id;
    private int service_id;
    private int course;
    private int category;

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

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
