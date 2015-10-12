package com.springapp.mvc;

import com.springapp.db.Departments;
import com.springapp.db.Employee;

import java.util.List;

/**
 * Created by hrvoje on 03.10.15..
 */
public interface EmployeeDeo {

    void insert(Employee employee);

    Employee selectById(int id);

    List<Employee> selectAll();

    void delete(int id);

    void update(Employee employee,int id);

    List <Departments> departments();
}
