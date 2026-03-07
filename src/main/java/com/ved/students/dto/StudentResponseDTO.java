package com.ved.students.dto;

public class StudentResponseDTO {

    private Integer rollNo;
    private String name;
    private String branch;
    private float percentage;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(Integer rollNo, String name, String branch, float percentage) {
        this.rollNo = rollNo;
        this.name = name;
        this.branch = branch;
        this.percentage = percentage;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}