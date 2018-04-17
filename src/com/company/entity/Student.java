package com.company.entity;

public class Student {
    private int id;
    private String name;
    private String food;

    public Student(){}

    public Student(int id, String name, String food) {
        this.id = id;
        this.name = name;
        this.food = food;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

