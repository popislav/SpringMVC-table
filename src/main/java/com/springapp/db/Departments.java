package com.springapp.db;

/**
 * Created by hrvoje on 03.10.15..
 */
public class Departments {
    private String department;
    private int emplCount;


    public Departments() {

    }

    public Departments(String department, Integer emplCount) {
        this.department = department;
        this.emplCount = emplCount;
    }

    public String getDepartment(){ return department;}

    public void setDepartment(String department){this.department = department;}

    public int getEmployeeCount(){return emplCount;}

    public void setEmployeeCount(Integer emplCount){this.emplCount = emplCount;}
}
