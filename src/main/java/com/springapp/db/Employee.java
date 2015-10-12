package com.springapp.db;

/**
 * Created by hrvoje on 03.10.15..
 */
public class Employee {
    private int id;
    private String name;
    private String surname;
    private Integer experience;
    private String department;

    public Employee() {

    }

    public Employee(Integer id, String name,String surname, Integer experience, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
