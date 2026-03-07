package com.ved.students.dto;

public class StudentRequestDTO {
    private String name;
    private String branch;
    private float percentage;

    public StudentRequestDTO(){

    }

    public String getName() {
        return name;
    }
    public String getBranch() {
        return branch;
    }
    public float getPercentage() {
        return percentage;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

}
